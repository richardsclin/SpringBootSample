package com.fubon.portal;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fubon.demo.cross_service.ConfigService;
import com.fubon.portal.repository.Agent;
import com.fubon.portal.repository.AnnounceInfo;
import com.fubon.portal.repository.MyFavoriteMenu;
import com.fubon.security.JwtConfig;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import io.swagger.annotations.ApiParam;
//import io.swagger.annotations.ApiResponse;
//import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Portal", description = "the Portal API")
@EnableFeignClients(basePackages = { "com.fubon.demo.cross_service" })
@RestController
@RequestMapping(value = "/api")
public class PortalController {
	@Autowired
	private PortalService portalService;
	
	@Autowired
	ConfigService configService;
	

	@Operation(summary = "取得代理人清單", description = "列出期間內代理人清單")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/agent/{agentId}/{checkDate}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Agent> findAgentist(@Parameter(required = true, name = "agentId", description = "代理人代碼" ) @PathVariable String agentId,
    		@Parameter(name = "checkDate", description = "檢查時間(YYYY-MM-DD)") @PathVariable String checkDate) 
	{
		if (checkDate.equals(""))
			checkDate =  new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	    
		List<Agent> agentList = this.portalService.findAgent(agentId, checkDate);
		
		return agentList;
	}
	
	//設定我的最愛
	@Operation(summary = "記錄我的最愛", description = "記錄我的最愛")
    @ApiResponses(value = {
    		@ApiResponse(responseCode  = "201", description = "記錄成功")})
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/menu/myFavorite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MyFavoriteMenu createFavoriteInfo(@Parameter(required = true, description = "我的最愛內容") @RequestBody MyFavoriteMenu myFavorite) {
		
		return this.portalService.AddMyFavoriteMenu(myFavorite);
    }
	
	@Operation(summary = "更新我的最愛", description = "更新我的最愛內容")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "存檔成功")})
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/menu/myFavorite", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MyFavoriteMenu update(@Parameter(required = true, description = "我的最愛內容") @RequestBody MyFavoriteMenu myFavorite) {
    	return this.portalService.updateMyFavoriteMenu(myFavorite);
    }
	
	@Operation(summary = "刪除我的最愛", description = "刪除我的最愛內容")
	@DeleteMapping("/menu/myFavorite/{userId}/{itemId}")
	@ApiResponses(value = {@ApiResponse(responseCode = "200", description = "刪除成功")})
	void deleteFavoriteSysMenu(@Parameter(required = true, name = "userId", description = "使用者代碼") @PathVariable String userId,
			@Parameter(required = true, name = "itemId", description = "功能代碼") @PathVariable Long itemId) 
	{
		this.portalService.deleteFavoriteMenu(userId, itemId);
		
	}
	 
	 @Operation(summary = "取得公告(非過期與過期請分開抓)", description = "列出公告(非過期與過期請分開抓)")
	 @ResponseStatus(HttpStatus.OK)
	 @GetMapping(value = "/announceInfo", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public PageInfo<AnnounceInfo> findAnnounceInfoList(
			 @Parameter(name = "checkDate", description = "檢查時間 (YYYY-MM-DD)") @RequestParam(value = "checkDate", defaultValue="") String checkDate,
			 @Parameter(required=true, name="showExpired", description="顯示過期") @RequestParam(value="showExpired", defaultValue="false") boolean showExpired,
			 @Parameter(name="page", description="頁數") @RequestParam(value="page", defaultValue="1") Integer page,
			 @Parameter(name="size", description="筆數") @RequestParam(value="size", defaultValue="5") Integer size) 
	 {
		if (checkDate.equals(""))
			checkDate =  new SimpleDateFormat("yyyy-MM-dd").format(new Date(System.currentTimeMillis()));
	    PageHelper.startPage(page,size);
	    PageInfo<AnnounceInfo> announceInfoList = new PageInfo<>(this.portalService.findAnnounceInfoList(showExpired, checkDate));
		//List<AnnounceInfo> announceInfoList = this.portalService.findAnnounceInfoList(includeExpired) ; 
		return announceInfoList;
	 }
	 
	 
	 @Operation(summary = "Test Cross Service", description = "Test Cross Service")
	 @ResponseStatus(HttpStatus.OK)
	 @GetMapping(value = "/cross_service", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	 public String testCrossService() 
	 {
		return configService.healthCheck();
	 }
}
