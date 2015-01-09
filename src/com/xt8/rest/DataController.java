package com.xt8.rest;

import static com.xt8.util.Constants.CITY_LIST;
import static com.xt8.util.Constants.DISTRICT_LIST;
import static com.xt8.util.Constants.EXPRESSCORP_LIST;
import static com.xt8.util.Constants.FIRST_CATEGORY_LIST;
import static com.xt8.util.Constants.MESSAGE;
import static com.xt8.util.Constants.PROVINCE_LIST;
import static com.xt8.util.Constants.SECOND_CATEGORY_LIST;
import static com.xt8.util.Constants.STATUS;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import com.xt8.model.Category;
import com.xt8.model.City;
import com.xt8.model.District;
import com.xt8.model.ExpressCorp;
import com.xt8.model.Province;
import com.xt8.service.CategoryService;
import com.xt8.service.CityService;
import com.xt8.service.DistrictService;
import com.xt8.service.ExpressCorpService;
import com.xt8.service.ProvinceService;
import com.xt8.util.StringUtil;

@Path("/data")
public class DataController {

	@Resource(name = "provinceService")
	private ProvinceService provinceService;

	@Resource(name = "cityService")
	private CityService cityService;

	@Resource(name = "districtService")
	private DistrictService districtService;

	@Resource(name = "categoryService")
	private CategoryService categoryService;

	@Resource(name = "expressCorpService")
	private ExpressCorpService expressCorpService;

	private JSONObject json = new JSONObject();

	@POST
	@Path("getProvince.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getProvince(@FormParam("apiKey") String apiKey,
			@FormParam("myId") String myId, @Context HttpServletRequest request) {
		String[] strs = { apiKey, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(PROVINCE_LIST, null);
			return json.toString();
		}

		List<Province> list = provinceService.listProvinces();
		JSONArray provinces = new JSONArray();
		for (Province province : list) {
			provinces.add(province.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(PROVINCE_LIST, provinces.toString());
		System.out.println(provinces.toString());
		return json.toString();
	}

	@POST
	@Path("getCity.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getCity(@FormParam("apiKey") String apiKey,
			@FormParam("provinceId") String provinceId,
			@FormParam("myId") String myId, @Context HttpServletRequest request) {
		String[] strs = { apiKey, provinceId, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(CITY_LIST, null);
			return json.toString();
		}

		Integer pId = Integer.parseInt(provinceId);
		List<City> cityList = cityService.findByProvinceId(pId);
		JSONArray cities = new JSONArray();
		for (City city : cityList) {
			cities.add(city.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(CITY_LIST, cities.toString());
		System.out.println(cities.toString());
		return json.toString();
	}

	@POST
	@Path("getDistrict.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getDistrict(@FormParam("apiKey") String apiKey,
			@FormParam("cityId") String cityId, @FormParam("myId") String myId,
			@Context HttpServletRequest request) {
		String[] strs = { apiKey, cityId, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(DISTRICT_LIST, null);
			return json.toString();
		}

		Integer cId = Integer.parseInt(cityId);
		List<District> list = districtService.findByCityId(cId);
		JSONArray districts = new JSONArray();
		for (District district : list) {
			districts.add(district.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(DISTRICT_LIST, districts.toString());
		System.out.println(districts.toString());
		return json.toString();
	}

	@POST
	@Path("getFirstCategory.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getFirstCategory(@FormParam("apiKey") String apiKey,
			@FormParam("myId") String myId, @Context HttpServletRequest request) {
		String[] strs = { apiKey, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(FIRST_CATEGORY_LIST, null);
			return json.toString();
		}

		Category root = categoryService.getRootCategory();
		List<Category> list = categoryService.findByByParent(root);
		JSONArray districts = new JSONArray();
		for (Category category : list) {
			districts.add(category.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(FIRST_CATEGORY_LIST, districts.toString());
		System.out.println(districts.toString());
		return json.toString();
	}

	@POST
	@Path("getSecondCategory.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getSecondCategory(@FormParam("apiKey") String apiKey,
			@FormParam("firstId") String firstId,
			@FormParam("myId") String myId, @Context HttpServletRequest requst) {
		String[] strs = { apiKey, firstId, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(SECOND_CATEGORY_LIST, null);
			return json.toString();
		}

		Integer cateId = Integer.parseInt(firstId);
		Category firstCate = categoryService.findById(cateId);
		List<Category> list = categoryService.findByByParent(firstCate);
		JSONArray districts = new JSONArray();
		for (Category category : list) {
			districts.add(category.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(SECOND_CATEGORY_LIST, districts.toString());
		System.out.println(districts.toString());
		return json.toString();
	}

	@POST
	@Path("getExpressCorp.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String getExpressCorp(@FormParam("apiKey") String apiKey,
			@FormParam("myId") String myId, @Context HttpServletRequest request) {
		String[] strs = { apiKey, myId };
		if (StringUtil.haveNullOrBlank(strs)) {// 提交参数没有无效值
			// 提交参数无效
			json.put(STATUS, -1);
			json.put(MESSAGE, "操作失败,参数异常");
			json.put(EXPRESSCORP_LIST, null);
			return json.toString();
		}

		List<ExpressCorp> list = expressCorpService.listExpressCorps();
		JSONArray districts = new JSONArray();
		for (ExpressCorp expressCorp : list) {
			districts.add(expressCorp.toSimpleJSON());
		}

		json.put(STATUS, 1);
		json.put(MESSAGE, "查询成功");
		json.put(EXPRESSCORP_LIST, districts.toString());
		System.out.println(districts.toString());
		return json.toString();
	}

	@GET
	@Path("test.do")
	@Produces(MediaType.APPLICATION_JSON)
	public String test() {
		json.put("mobile", "111111112");
		json.put("passwd", "123456");
		return json.toString();
	}

}
