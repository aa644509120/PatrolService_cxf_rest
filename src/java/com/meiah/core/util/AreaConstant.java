﻿/**A级**/
package com.meiah.core.util;

import java.util.HashMap;
import java.util.Map;

public class AreaConstant {
	private static Map<String, String> map = new HashMap<String, String>();
	public static String getLatlng(String area){
		return map.get(area);
	}
	static{
		map.put("北京","39.9,116.4");
		map.put("北京 天安门","39.9,116.38");  //维度，经度
		map.put("北京 东城区","39.93,116.42");
		map.put("北京 西城区","39.92,116.37");
		map.put("北京 崇文区","39.88,116.43");
		map.put("北京 宣武区","39.87,116.35");
		map.put("北京 朝阳区","39.92,116.43");
		map.put("北京 丰台区","39.85,116.28");
		map.put("北京 石景山区","39.9,116.22");
		map.put("北京 海淀区","39.95,116.3");
		map.put("北京 门头沟区","39.93,116.1");
		map.put("北京 房山区","39.75,116.13");
		map.put("北京 通州区","39.92,116.65");
		map.put("北京 顺义区","40.13,116.65");
		map.put("北京 昌平区","40.22,116.23");
		map.put("北京 大兴区","39.73,116.33");
		map.put("北京 怀柔区","40.32,116.63");
		map.put("北京 平谷区","40.13,117.12");
		map.put("北京 密云县","40.37,116.83");
		map.put("北京 延庆县","40.45,115.97");
		map.put("天津","39.12,117.2");
		map.put("天津 和平区","39.12,117.2");
		map.put("天津 河东区","39.12,117.22");
		map.put("天津 河西区","39.12,117.22");
		map.put("天津 南开区","39.13,117.15");
		map.put("天津 河北区","39.15,117.18");
		map.put("天津 红桥区","39.17,117.15");
		map.put("天津 塘沽区","39.02,117.65");
		map.put("天津 汉沽区","39.25,117.8");
		map.put("天津 大港区","38.83,117.45");
		map.put("天津 东丽区","39.08,117.3");
		map.put("天津 西青区","39.13,117.0");
		map.put("天津 津南区","38.98,117.38");
		map.put("天津 北辰区","39.22,117.13");
		map.put("天津 武清区","39.38,117.03");
		map.put("天津 宝坻区","39.72,117.3");
		map.put("天津 滨海新区","39.03,117.68");
		map.put("天津 宁河县","39.33,117.82");
		map.put("天津 静海县","38.93,116.92");
		map.put("天津 蓟县","40.05,117.4");
		map.put("河北 石家庄","38.05,114.52");
		map.put("河北 唐山","39.63,118.2");
		map.put("河北 秦皇岛","39.93,119.6");
		map.put("河北 邯郸","36.62,114.48");
		map.put("河北 邢台","37.07,114.48");
		map.put("河北 保定","38.87,115.47");
		map.put("河北 张家口","40.82,114.88");
		map.put("河北 承德","40.97,117.93");
		map.put("河北 沧州","38.3,116.83");
		map.put("河北 廊坊","39.52,116.7");
		map.put("河北 衡水","37.73,115.68");
		map.put("山西 太原","37.87,112.55");
		map.put("山西 大同","40.08,113.3");
		map.put("山西 阳泉","37.85,113.57");
		map.put("山西 长治","36.2,113.12");
		map.put("山西 晋城","35.5,112.83");
		map.put("山西 朔州","39.33,112.43");
		map.put("山西 晋中","37.68,112.75");
		map.put("山西 运城","35.02,110.98");
		map.put("山西 忻州","38.42,112.73");
		map.put("山西 临汾","36.08,111.52");
		map.put("山西 吕梁","37.52,111.13");
		map.put("内蒙古 呼和浩特","40.83,111.73");
		map.put("内蒙古 包头","40.65,109.83");
		map.put("内蒙古 乌海","39.67,106.82");
		map.put("内蒙古 赤峰","42.27,118.92");
		map.put("内蒙古 通辽","43.62,122.27");
		map.put("内蒙古 鄂尔多斯","39.62,109.8");
		map.put("内蒙古 呼伦贝尔","49.22,119.77");
		map.put("内蒙古 巴彦淖尔","40.75,107.42");
		map.put("内蒙古 乌兰察布","40.98,113.12");
		map.put("内蒙古 兴安盟","46.08,122.05");
		map.put("内蒙古 锡林郭勒盟","43.95,116.07");
		map.put("内蒙古 阿拉善盟","38.83,105.67");
		map.put("辽宁 沈阳","41.8,123.43");
		map.put("辽宁 大连","38.92,121.62");
		map.put("辽宁 鞍山","41.1,122.98");
		map.put("辽宁 抚顺","41.88,123.98");
		map.put("辽宁 本溪","41.3,123.77");
		map.put("辽宁 丹东","40.13,124.38");
		map.put("辽宁 锦州","41.1,121.13");
		map.put("辽宁 营口","40.67,122.23");
		map.put("辽宁 阜新","42.02,121.67");
		map.put("辽宁 辽阳","41.27,123.17");
		map.put("辽宁 盘锦","41.12,122.07");
		map.put("辽宁 铁岭","42.28,123.83");
		map.put("辽宁 朝阳","41.57,120.45");
		map.put("辽宁 葫芦岛","40.72,120.83");
		map.put("吉林 长春","43.9,125.32");
		map.put("吉林","43.83,126.55");
		map.put("吉林 四平","43.17,124.35");
		map.put("吉林 辽源","42.88,125.13");
		map.put("吉林 通化","41.73,125.93");
		map.put("吉林 白山","41.93,126.42");
		map.put("吉林 松原","45.13,124.82");
		map.put("吉林 白城","45.62,122.83");
		map.put("吉林 延边朝鲜族自治州","42.88,129.5");
		map.put("黑龙江 哈尔滨","45.8,126.53");
		map.put("黑龙江 齐齐哈尔","47.33,123.95");
		map.put("黑龙江 鸡西","45.3,130.97");
		map.put("黑龙江 鹤岗","47.33,130.27");
		map.put("黑龙江 双鸭山","46.63,131.15");
		map.put("黑龙江 大庆","46.58,125.03");
		map.put("黑龙江 伊春","47.73,128.9");
		map.put("黑龙江 佳木斯","46.82,130.37");
		map.put("黑龙江 七台河","45.78,130.95");
		map.put("黑龙江 牡丹江","44.58,129.6");
		map.put("黑龙江 黑河","50.25,127.48");
		map.put("黑龙江 绥化","46.63,126.98");
		map.put("黑龙江 大兴安岭","50.42,124.12");
		map.put("上海","31.23,121.47");
		map.put("上海 黄浦区","31.23,121.48");
		map.put("上海 卢湾区","31.22,121.47");
		map.put("上海 徐汇区","31.18,121.43");
		map.put("上海 长宁区","31.22,121.42");
		map.put("上海 静安区","31.23,121.45");
		map.put("上海 普陀区","31.25,121.4");
		map.put("上海 闸北区","31.25,121.45");
		map.put("上海 虹口区","31.27,121.5");
		map.put("上海 杨浦区","31.27,121.52");
		map.put("上海 闵行区","31.12,121.38");
		map.put("上海 宝山区","31.4,121.48");
		map.put("上海 嘉定区","31.38,121.27");
		map.put("上海 浦东新区","31.22,121.53");
		map.put("上海 金山区","30.75,121.33");
		map.put("上海 松江区","31.03,121.22");
		map.put("上海 青浦区","31.15,121.12");
		map.put("上海 南汇区","31.05,121.75");
		map.put("上海 奉贤区","30.92,121.47");
		map.put("上海 崇明县","31.62,121.4");
		map.put("江苏 南京","32.07,118.78");
		map.put("江苏 无锡","31.57,120.3");
		map.put("江苏 徐州","34.27,117.18");
		map.put("江苏 常州","31.78,119.95");
		map.put("江苏 苏州","31.3,120.58");
		map.put("江苏 南通","31.98,120.88");
		map.put("江苏 连云港","34.6,119.22");
		map.put("江苏 淮安","33.62,119.02");
		map.put("江苏 盐城","33.35,120.15");
		map.put("江苏 扬州","32.4,119.4");
		map.put("江苏 镇江","32.2,119.45");
		map.put("江苏 泰州","32.45,119.92");
		map.put("江苏 宿迁","33.97,118.28");
		map.put("浙江 杭州","30.28,120.15");
		map.put("浙江 宁波","29.88,121.55");
		map.put("浙江 温州","28.0,120.7");
		map.put("浙江 嘉兴","30.75,120.75");
		map.put("浙江 湖州","30.9,120.08");
		map.put("浙江 绍兴","30.0,120.57");
		map.put("浙江 金华","29.08,119.65");
		map.put("浙江 衢州","28.93,118.87");
		map.put("浙江 舟山","30.0,122.2");
		map.put("浙江 台州","28.68,121.43");
		map.put("浙江 丽水","28.45,119.92");
		map.put("安徽 合肥","31.83,117.25");
		map.put("安徽 芜湖","31.33,118.38");
		map.put("安徽 蚌埠","32.92,117.38");
		map.put("安徽 淮南","32.63,117.0");
		map.put("安徽 马鞍山","31.7,118.5");
		map.put("安徽 淮北","33.95,116.8");
		map.put("安徽 铜陵","30.93,117.82");
		map.put("安徽 安庆","30.53,117.05");
		map.put("安徽 黄山","29.72,118.33");
		map.put("安徽 滁州","32.3,118.32");
		map.put("安徽 阜阳","32.9,115.82");
		map.put("安徽 宿州","33.63,116.98");
		map.put("安徽 巢湖","31.6,117.87");
		map.put("安徽 六安","31.77,116.5");
		map.put("安徽 亳州","33.85,115.78");
		map.put("安徽 池州","30.67,117.48");
		map.put("安徽 宣城","30.95,118.75");
		map.put("福建 福州","26.08,119.3");
		map.put("福建 厦门","24.48,118.08");
		map.put("福建 莆田","25.43,119.0");
		map.put("福建 三明","26.27,117.62");
		map.put("福建 泉州","24.88,118.67");
		map.put("福建 漳州","24.52,117.65");
		map.put("福建 南平","26.65,118.17");
		map.put("福建 龙岩","25.1,117.03");
		map.put("福建 宁德","26.67,119.52");
		map.put("江西 南昌","28.68,115.85");
		map.put("江西 景德镇","29.27,117.17");
		map.put("江西 萍乡","27.63,113.85");
		map.put("江西 九江","29.7,116.0");
		map.put("江西 新余","27.82,114.92");
		map.put("江西 鹰潭","28.27,117.07");
		map.put("江西 赣州","25.83,114.93");
		map.put("江西 吉安","27.12,114.98");
		map.put("江西 宜春","27.8,114.38");
		map.put("江西 抚州","28.0,116.35");
		map.put("江西 上饶","28.45,117.97");
		map.put("山东 济南","36.67,116.98");
		map.put("山东 青岛","36.07,120.38");
		map.put("山东 淄博","36.82,118.05");
		map.put("山东 枣庄","34.82,117.32");
		map.put("山东 东营","37.43,118.67");
		map.put("山东 烟台","37.45,121.43");
		map.put("山东 潍坊","36.7,119.15");
		map.put("山东 济宁","35.42,116.58");
		map.put("山东 泰安","36.2,117.08");
		map.put("山东 威海","37.52,122.12");
		map.put("山东 日照","35.42,119.52");
		map.put("山东 莱芜","36.22,117.67");
		map.put("山东 临沂","35.05,118.35");
		map.put("山东 德州","37.45,116.3");
		map.put("山东 聊城","36.45,115.98");
		map.put("山东 滨州","37.38,117.97");
		map.put("河南 郑州","34.75,113.62");
		map.put("河南 开封","34.8,114.3");
		map.put("河南 洛阳","34.62,112.45");
		map.put("河南 平顶山","33.77,113.18");
		map.put("河南 安阳","36.1,114.38");
		map.put("河南 鹤壁","35.75,114.28");
		map.put("河南 新乡","35.3,113.9");
		map.put("河南 焦作","35.22,113.25");
		map.put("河南 濮阳","35.77,115.03");
		map.put("河南 许昌","34.03,113.85");
		map.put("河南 漯河","33.58,114.02");
		map.put("河南 三门峡","34.78,111.2");
		map.put("河南 南阳","33.0,112.52");
		map.put("河南 商丘","34.45,115.65");
		map.put("河南 信阳","32.13,114.07");
		map.put("河南 周口","33.62,114.65");
		map.put("河南 驻马店","32.98,114.02");
		map.put("湖北 武汉","30.6,114.3");
		map.put("湖北 黄石","30.2,115.03");
		map.put("湖北 十堰","32.65,110.78");
		map.put("湖北 宜昌","30.7,111.28");
		map.put("湖北 襄樊","32.02,112.15");
		map.put("湖北 鄂州","30.4,114.88");
		map.put("湖北 荆门","31.03,112.2");
		map.put("湖北 孝感","30.93,113.92");
		map.put("湖北 荆州","30.33,112.23");
		map.put("湖北 黄冈","30.45,114.87");
		map.put("湖北 咸宁","29.85,114.32");
		map.put("湖北 随州","31.72,113.37");
		map.put("湖北 恩施土家族苗族自治州","30.3,109.47");
		map.put("湖北 仙桃","30.37,113.45");
		map.put("湖南 长沙","28.23,112.93");
		map.put("湖南 株洲","27.83,113.13");
		map.put("湖南 湘潭","27.83,112.93");
		map.put("湖南 衡阳","26.9,112.57");
		map.put("湖南 邵阳","27.25,111.47");
		map.put("湖南 岳阳","29.37,113.12");
		map.put("湖南 常德","29.05,111.68");
		map.put("湖南 张家界","29.13,110.47");
		map.put("湖南 益阳","28.6,112.32");
		map.put("湖南 郴州","25.78,113.02");
		map.put("湖南 永州","26.43,111.62");
		map.put("湖南 怀化","27.57,110.0");
		map.put("湖南 娄底","27.73,112.0");
		map.put("湖南 湘西土家族苗族自治州","28.32,109.73");
		map.put("广东 广州","23.13,113.27");
		map.put("广东 韶关","24.82,113.6");
		map.put("广东 深圳","22.55,114.05");
		map.put("广东 珠海","22.27,113.57");
		map.put("广东 汕头","23.35,116.68");
		map.put("广东 佛山","23.02,113.12");
		map.put("广东 江门","22.58,113.08");
		map.put("广东 湛江","21.27,110.35");
		map.put("广东 茂名","21.67,110.92");
		map.put("广东 肇庆","23.05,112.47");
		map.put("广东 惠州","23.12,114.42");
		map.put("广东 梅州","24.28,116.12");
		map.put("广东 汕尾","22.78,115.37");
		map.put("广东 河源","23.73,114.7");
		map.put("广东 阳江","21.87,111.98");
		map.put("广东 清远","23.7,113.03");
		map.put("广东 东莞","23.05,113.75");
		map.put("广东 中山","22.52,113.38");
		map.put("广东 潮州","23.67,116.62");
		map.put("广东 揭阳","23.55,116.37");
		map.put("广东 云浮","22.92,112.03");
		map.put("广西 南宁","22.82,108.37");
		map.put("广西 柳州","24.33,109.42");
		map.put("广西 桂林","25.28,110.28");
		map.put("广西 梧州","23.48,111.27");
		map.put("广西 北海","21.48,109.12");
		map.put("广西 防城港","21.7,108.35");
		map.put("广西 钦州","21.95,108.62");
		map.put("广西 贵港","23.1,109.6");
		map.put("广西 玉林","22.63,110.17");
		map.put("广西 百色","23.9,106.62");
		map.put("广西 贺州","24.42,111.55");
		map.put("广西 河池","24.7,108.07");
		map.put("广西 来宾","23.73,109.23");
		map.put("广西 崇左","22.4,107.37");
		map.put("海南 海口","20.03,110.32");
		map.put("海南 三亚","18.25,109.5");
		map.put("海南 五指山","18.78,109.52");
		map.put("重庆","29.57,106.55");
		map.put("重庆 万州区","30.82,108.4");
		map.put("重庆 涪陵区","29.72,107.4");
		map.put("重庆 渝中区","29.55,106.57");
		map.put("重庆 大渡口区","29.48,106.48");
		map.put("重庆 江北区","29.6,106.57");
		map.put("重庆 沙坪坝区","29.53,106.45");
		map.put("重庆 九龙坡区","29.5,106.5");
		map.put("重庆 南岸区","29.52,106.57");
		map.put("重庆 北碚区","29.8,106.4");
		map.put("重庆 万盛区","28.97,106.92");
		map.put("重庆 双桥区","29.48,105.78");
		map.put("重庆 渝北区","29.72,106.63");
		map.put("重庆 巴南区","29.38,106.52");
		map.put("重庆 黔江区","29.53,108.77");
		map.put("重庆 长寿区","29.87,107.08");
		map.put("重庆 綦江县","29.03,106.65");
		map.put("重庆 潼南县","30.18,105.83");
		map.put("重庆 铜梁县","29.85,106.05");
		map.put("重庆 大足县","29.7,105.72");
		map.put("重庆 荣昌县","29.4,105.58");
		map.put("重庆 璧山县","29.6,106.22");
		map.put("重庆 梁平县","30.68,107.8");
		map.put("重庆 城口县","31.95,108.67");
		map.put("重庆 丰都县","29.87,107.73");
		map.put("重庆 垫江县","30.33,107.35");
		map.put("重庆 武隆县","29.33,107.75");
		map.put("重庆 忠县","30.3,108.02");
		map.put("重庆 开县","31.18,108.42");
		map.put("重庆 云阳县","30.95,108.67");
		map.put("重庆 奉节县","31.02,109.47");
		map.put("重庆 巫山县","31.08,109.88");
		map.put("重庆 巫溪县","31.4,109.63");
		map.put("重庆 石柱土家族自治县","30.0,108.12");
		map.put("重庆 秀山土家族苗族自治县","28.45,108.98");
		map.put("重庆 酉阳土家族苗族自治县","28.85,108.77");
		map.put("重庆 彭水苗族土家族自治县","29.3,108.17");
		map.put("四川 成都","30.67,104.07");
		map.put("四川 自贡","29.35,104.78");
		map.put("四川 攀枝花","26.58,101.72");
		map.put("四川 泸州","28.87,105.43");
		map.put("四川 德阳","31.13,104.38");
		map.put("四川 绵阳","31.47,104.73");
		map.put("四川 广元","32.43,105.83");
		map.put("四川 遂宁","30.52,105.57");
		map.put("四川 内江","29.58,105.05");
		map.put("四川 乐山","29.57,103.77");
		map.put("四川 南充","30.78,106.08");
		map.put("四川 眉山","30.05,103.83");
		map.put("四川 宜宾","28.77,104.62");
		map.put("四川 广安","30.47,106.63");
		map.put("四川 达州","31.22,107.5");
		map.put("四川 雅安","29.98,103.0");
		map.put("四川 巴中","31.85,106.77");
		map.put("四川 资阳","30.12,104.65");
		map.put("四川 阿坝","31.9,102.22");
		map.put("四川 甘孜","30.05,101.97");
		map.put("四川 凉山","27.9,102.27");
		map.put("贵州 贵阳","26.65,106.63");
		map.put("贵州 六盘水","26.6,104.83");
		map.put("贵州 遵义","27.73,106.92");
		map.put("贵州 安顺","26.25,105.95");
		map.put("贵州 铜仁","27.72,109.18");
		map.put("贵州 兴义","25.08,104.9");
		map.put("贵州 毕节","27.3,105.28");
		map.put("贵州 黔东南","26.58,107.97");
		map.put("贵州 黔南","26.27,107.52");
		map.put("云南 昆明","25.05,102.72");
		map.put("云南 曲靖","25.5,103.8");
		map.put("云南 玉溪","24.35,102.55");
		map.put("云南 保山","25.12,99.17");
		map.put("云南 昭通","27.33,103.72");
		map.put("云南 丽江","26.88,100.23");
		map.put("云南 墨江","23.43,101.68");
		map.put("云南 临沧","23.88,100.08");
		map.put("云南 楚雄","25.03,101.55");
		map.put("云南 红河","23.37,103.4");
		map.put("云南 文山","23.37,104.25");
		map.put("云南 西双版纳","22.02,100.8");
		map.put("云南 大理","25.6,100.23");
		map.put("云南 德宏","24.43,98.58");
		map.put("云南 怒江","25.85,98.85");
		map.put("云南 迪庆","27.83,99.7");
		map.put("西藏 拉萨","29.65,91.13");
		map.put("西藏 昌都","31.13,97.18");
		map.put("西藏 山南","29.23,91.77");
		map.put("西藏 日喀则","29.27,88.88");
		map.put("西藏 那曲","31.48,92.07");
		map.put("西藏 阿里","32.5,80.1");
		map.put("西藏 林芝","29.68,94.37");
		map.put("陕西 西安","34.27,108.93");
		map.put("陕西 铜川","34.9,108.93");
		map.put("陕西 宝鸡","34.37,107.13");
		map.put("陕西 咸阳","34.33,108.7");
		map.put("陕西 渭南","34.5,109.5");
		map.put("陕西 延安","36.6,109.48");
		map.put("陕西 汉中","33.07,107.02");
		map.put("陕西 榆林","38.28,109.73");
		map.put("陕西 安康","32.68,109.02");
		map.put("陕西 商洛","33.87,109.93");
		map.put("甘肃 兰州","36.07,103.82");
		map.put("甘肃 嘉峪关","39.8,98.27");
		map.put("甘肃 金昌","38.5,102.18");
		map.put("甘肃 白银","36.55,104.18");
		map.put("甘肃 天水","34.58,105.72");
		map.put("甘肃 武威","37.93,102.63");
		map.put("甘肃 张掖","38.93,100.45");
		map.put("甘肃 平凉","35.55,106.67");
		map.put("甘肃 酒泉","39.75,98.52");
		map.put("甘肃 庆阳","35.73,107.63");
		map.put("甘肃 定西","35.58,104.62");
		map.put("甘肃 陇南","33.4,104.92");
		map.put("甘肃 临夏","35.6,103.22");
		map.put("甘肃 甘南","34.98,102.92");
		map.put("青海 西宁","36.62,101.78");
		map.put("青海 海东","36.5,102.12");
		map.put("青海 海北","36.97,100.9");
		map.put("青海 黄南","35.52,102.02");
		map.put("青海 海南","36.28,100.62");
		map.put("青海 果洛","34.48,100.23");
		map.put("青海 玉树","33.0,97.02");
		map.put("青海 海西","37.37,97.37");
		map.put("宁夏 银川","38.47,106.28");
		map.put("宁夏 石嘴山","39.02,106.38");
		map.put("宁夏 吴忠","37.98,106.2");
		map.put("宁夏 固原","36.0,106.28");
		map.put("宁夏 中卫","37.52,105.18");
		map.put("新疆 乌鲁木齐","43.82,87.62");
		map.put("新疆 克拉玛依","45.6,84.87");
		map.put("新疆 吐鲁番","42.95,89.17");
		map.put("新疆 哈密","42.83,93.52");
		map.put("新疆 昌吉","44.02,87.3");
		map.put("新疆 博尔塔拉","44.9,82.07");
		map.put("新疆 巴音郭楞","41.77,86.15");
		map.put("新疆 阿克苏","41.17,80.27");
		map.put("新疆 阿图什","39.72,76.17");
		map.put("新疆 喀什","39.47,75.98");
		map.put("新疆 和田","37.12,79.92");
		map.put("新疆 伊犁","43.92,81.32");
		map.put("新疆 塔城","46.75,82.98");
		map.put("新疆 阿勒泰","47.85,88.13");
		map.put("新疆 石河子","44.3,86.03");
		map.put("香港","22.2,114.08");
		map.put("澳门","22.13,113.33");
		map.put("台湾 台北市","25.03,121.5");
		map.put("台湾 高雄市","22.62,120.28");
		map.put("台湾 基隆市","25.13,121.73");
		map.put("台湾 台中市","24.15,120.67");
		map.put("台湾 台南市","23.0,120.2");
		map.put("台湾 新竹市","24.82,120.95");
		map.put("台湾 嘉义市","23.48,120.43");
		map.put("台湾 台北县","25.02,121.47");
		map.put("台湾 宜兰县","24.77,121.75");
		map.put("台湾 桃园县","24.97,121.3");
		map.put("台湾 苗栗县","24.53,120.8");
		map.put("台湾 台中县","24.25,120.72");
		map.put("台湾 彰化县","24.08,120.53");
		map.put("台湾 南投县","23.92,120.67");
		map.put("台湾 云林县","23.72,120.53");
		map.put("台湾 台南县","23.32,120.32");
		map.put("台湾 高雄县","22.63,120.37");
		map.put("台湾 屏东县","22.67,120.48");
		map.put("台湾 台东县","22.75,121.15");
		map.put("台湾 花莲县","23.98,121.6");
		map.put("台湾 澎湖县","23.58,119.58");
		map.put("山东 菏泽","35.25,115.43");
		map.put("台湾","25.03,121.5");


	}
}
