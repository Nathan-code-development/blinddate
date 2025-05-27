<template>
	<view class="content">
		<view class="sub-content">
			<view class="panel">
				<!-- 个人信息:年龄,户籍,爱好,学历,工作,年薪,车房,简单描述 -->
				<h1 class="personal-information">个人信息</h1>
				<view class="name">
					<p class="name-text">姓名:</p>
					<input type="text" placeholder="请输入你的姓名:" class="name-input" v-model="user_info.name" />
				</view>
				<view class="age">
					<p class="age-text">年龄:</p>
					<picker :value="user_info.age" :range="list_age" @change="bindAgeChange" class="age-picker"
						v-model="list_age[user_info.age]">
						<view class="uni-input">{{list_age[user_info.age]}}</view>
					</picker>
				</view>
				<view class="gender">
					<p class="gender-text">性别:</p>
					<picker :value="user_info.gender" :range="gender_list" @change="bindGenderChange"
						class="gender-picker" v-model="gender_list[user_info.gender]">
						<view class="uni-input">{{gender_list[user_info.gender]}}</view>
					</picker>
				</view>
				<view class="household-registration">
					<p class="household-registration-text">户籍:</p>
					<picker :value="user_info.household_registration" :range="my_household_registration"
						@change="bindHouseholdRegistrationChange" class="household-registration-picker"
						v-model="my_household_registration[user_info.household_registration]">
						<view class="uni-input">{{my_household_registration[user_info.household_registration]}}</view>
					</picker>
				</view>
				<view class="hobby">
					<p class="hobby-text">爱好:</p>
					<input type="text" class="hobby-input" placeholder="填写你的爱好(没有填无)" v-model="user_info.hobby" />
				</view>
				<view class="education-background">
					<p class="education-background-text">学历:</p>
					<picker :value="user_info.education_background" :range="education_background_list"
						@change="bindEducationBackgroundChange" class="education-background-picker"
						v-model="education_background_list[user_info.education_background]">
						<view class="uni-input">{{education_background_list[user_info.education_background]}}</view>
					</picker>
				</view>
				<view class="occupation">
					<p class="occupation-text">工作:</p>
					<input type="text" class="occupation-input" placeholder="填写你的工作(没有填无)"
						v-model="user_info.occupation" />
				</view>
				<view class="salary-yearly">
					<p class="salary-yearly-text">年薪:</p>
					<picker :value="user_info.salary_yearly" :range="salary_yearly_list"
						@change="bindSalaryYearlyChange" class="salary-yearly-picker"
						v-model="salary_yearly_list[user_info.salary_yearly]">
						<view class="uni-input">{{salary_yearly_list[user_info.salary_yearly]}}</view>
					</picker>
				</view>
				<view class="car-and-house-status">
					<p class="car-and-house-status-text">车房:</p>
					<picker :value="user_info.car_and_house_status" :range="car_and_house_status_list"
						@change="bindCarAndHouseStatusChange" class="car-and-house-status-picker"
						v-model="car_and_house_status_list[user_info.car_and_house_status]">
						<view class="uni-input2">{{car_and_house_status_list[user_info.car_and_house_status]}}</view>
					</picker>
				</view>
				<view class="simple-introduction">
					<p class="simple-introduction-text">简介:</p>
					<textarea @blur="bindTextAreaBlur" auto-height class="simple-introduction-input" placeholder="自我介绍:"
						v-model="user_info.simple_introduction"></textarea>
				</view>
				<!-- 个人择偶意向:年龄,户籍,爱好,学历,工作,年薪,车房 -->
				<h1 class="expect-other-information">个人择偶意向</h1>
				<view class="expect-age">
					<p class="expect-age-text">年龄:</p>
					<picker :value="expect_people_info.age" :range="expect_age" @change="bindExpectAgeChange"
						class="expect-age-picker" v-model="expect_age[expect_people_info.age]">
						<view class="uni-input">{{expect_age[expect_people_info.age]}}</view>
					</picker>
				</view>
				<view class="expect-household-registration">
					<p class="expect-household-registration-text">户籍:</p>
					<picker :value="expect_people_info.household_registration" :range="expect_household_registration"
						@change="bindExpectHouseholdRegistrationChange" class="expect-household-registration-picker"
						v-model="expect_household_registration[expect_people_info.household_registration]">
						<view class="uni-input">
							{{expect_household_registration[expect_people_info.household_registration]}}</view>
					</picker>
				</view>
				<view class="expect-hobby">
					<p class="expect-hobby-text">爱好:</p>
					<input type="text" class="expect-hobby-input" placeholder="期望对象拥有的爱好(没有要求填无)"
						v-model="expect_people_info.hobby" />
				</view>
				<view class="expect-education-background">
					<p class="expect-education-background-text">学历:</p>
					<picker :value="expect_people_info.education_background" :range="expect_education_background_list"
						@change="bindExpectEducationBackgroundChange" class="expect-education-background-picker"
						v-model="expect_education_background_list[expect_people_info.education_background]">
						<view class="uni-input">
							{{expect_education_background_list[expect_people_info.education_background]}}</view>
					</picker>
				</view>
				<view class="expect-occupation">
					<p class="expect-occupation-text">工作:</p>
					<input type="text" class="expect-occupation-input" placeholder="期望对象拥有的工作(没有要求填无)"
						v-model="expect_people_info.occupation" />
				</view>
				<view class="expect-salary-yearly">
					<p class="expect-salary-yearly-text">年薪:</p>
					<picker :value="expect_people_info.salary_yearly" :range="expect_salary_yearly_list"
						@change="bindExpectSalaryYearlyChange" class="expect-salary-yearly-picker"
						v-model="expect_salary_yearly_list[expect_people_info.salary_yearly]">
						<view class="uni-input">{{expect_salary_yearly_list[expect_people_info.salary_yearly]}}</view>
					</picker>
				</view>
				<view class="expect-car-and-house-status">
					<p class="expect-car-and-house-status-text">车房:</p>
					<picker :value="expect_people_info.car_and_house_status" :range="expect_car_and_house_status_list"
						@change="bindExpectCarAndHouseStatusChange" class="expect-car-and-house-status-picker"
						v-model="expect_car_and_house_status_list[expect_people_info.car_and_house_status]">
						<view class="uni-input2">
							{{expect_car_and_house_status_list[expect_people_info.car_and_house_status]}}</view>
					</picker>
				</view>
				<button class="save-btn" @click="save">保存</button>
			</view>
			<image class="image"></image>
		</view>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				user_info: {
					head_image: "", //头像
					gender: "", //性别
					name: "", //名字
					age: 0, //年龄
					household_registration: "", //户籍
					hobby: "", //爱好
					education_background: "", //学历
					occupation: "", //工作
					salary_yearly: "", //年薪
					car_and_house_status: "", //车房
					simple_introduction: "" //简单描述(自我介绍)
				},
				expect_people_info: {
					age: 0, //年龄
					household_registration: "", //户籍
					hobby: "", //爱好
					education_background: "", //学历
					occupation: "", //工作
					salary_yearly: "", //年薪
					car_and_house_status: "" //车房
				},
				list_age: [0, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38,
					39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50
				],
				expect_age: ["0", "16-24", "25-30", "31-35", "36-40", "41-45", "46-50"],
				gender_list: ["男", "女"],
				my_household_registration: ['河北省石家庄市', '河北省唐山市', '河北省秦皇岛市', '河北省邯郸市', '河北省邢台市', '河北省保定市', '河北省张家口市',
					'河北省承德市', '河北省沧州市', '河北省廊坊市', '河北省衡水市', '山西省太原市', '山西省大同市', '山西省朔州市', '山西省忻州市', '山西省阳泉市', '山西省吕梁市',
					'山西省晋中市', '山西省长治市', '山西省晋城市', '山西省临汾市', '山西省运城市', '内蒙古自治区呼和浩特市', '内蒙古自治区包头市', '内蒙古自治区乌海市',
					'内蒙古自治区赤峰市', '内蒙古自治区通辽市', '内蒙古自治区鄂尔多斯市', '内蒙古自治区呼伦贝尔市', '内蒙古自治区巴彦淖尔市', '内蒙古自治区乌兰察布市', '辽宁省沈阳市',
					'辽宁省大连市', '辽宁省鞍山市', '辽宁省抚顺市', '辽宁省本溪市', '辽宁省丹东市', '辽宁省锦州市', '辽宁省营口市', '辽宁省阜新市', '辽宁省辽阳市', '辽宁省盘锦市',
					'辽宁省铁岭市', '辽宁省朝阳市', '辽宁省葫芦岛市', '吉林省长春市', '吉林省吉林市', '吉林省四平市', '吉林省通化市', '吉林省白城市', '吉林省辽源市',
					'吉林省松原市', '吉林省白山市', '黑龙江省哈尔滨市', '黑龙江省齐齐哈尔市', '黑龙江省牡丹江市', '黑龙江省佳木斯市', '黑龙江省大庆市', '黑龙江省鸡西市',
					'黑龙江省双鸭山市', '黑龙江省伊春市', '黑龙江省七台河市', '黑龙江省鹤岗市', '黑龙江省黑河市', '黑龙江省绥化市', '江苏省南京市', '江苏省无锡市', '江苏省徐州市',
					'江苏省常州市', '江苏省苏州市', '江苏省南通市', '江苏省连云港市', '江苏省淮安市', '江苏省盐城市', '江苏省扬州市', '江苏省镇江市', '江苏省泰州市',
					'江苏省宿迁市', '浙江省杭州市', '浙江省宁波市', '浙江省温州市', '浙江省绍兴市', '浙江省湖州市', '浙江省嘉兴市', '浙江省金华市', '浙江省衢州市', '浙江省台州市',
					'浙江省丽水市', '浙江省舟山市', '安徽省合肥市', '安徽省芜湖市', '安徽省蚌埠市', '安徽省淮南市', '安徽省马鞍山市', '安徽省淮北市', '安徽省铜陵市',
					'安徽省安庆市', '安徽省黄山市', '安徽省阜阳市', '安徽省宿州市', '安徽省滁州市', '安徽省六安市', '安徽省宣城市', '安徽省池州市', '安徽省亳州市', '福建省福州市',
					'福建省厦门市', '福建省漳州市', '福建省泉州市', '福建省三明市', '福建省莆田市', '福建省南平市', '福建省龙岩市', '福建省宁德市', '江西省南昌市', '江西省九江市',
					'江西省上饶市', '江西省抚州市', '江西省宜春市', '江西省吉安市', '江西省赣州市', '江西省景德镇市', '江西省萍乡市', '江西省新余市', '江西省鹰潭市',
					'山东省济南市', '山东省青岛市', '山东省淄博市', '山东省枣庄市', '山东省东营市', '山东省烟台市', '山东省潍坊市', '山东省济宁市', '山东省泰安市', '山东省威海市',
					'山东省日照市', '山东省滨州市', '山东省德州市', '山东省聊城市', '山东省临沂市', '山东省菏泽市', '河南省郑州市', '河南省开封市', '河南省洛阳市',
					'河南省平顶山市', '河南省安阳市', '河南省鹤壁市', '河南省新乡市', '河南省焦作市', '河南省濮阳市', '河南省许昌市', '河南省漯河市', '河南省三门峡市',
					'河南省商丘市', '河南省周口市', '河南省驻马店市', '河南省南阳市', '河南省信阳市', '湖北省武汉市', '湖北省黄石市', '湖北省十堰市', '湖北省宜昌市',
					'湖北省襄阳市', '湖北省鄂州市', '湖北省荆门市', '湖北省孝感市', '湖北省荆州市', '湖北省黄冈市', '湖北省咸宁市', '湖北省随州市', '湖南省长沙市', '湖南省株洲市',
					'湖南省湘潭市', '湖南省衡阳市', '湖南省邵阳市', '湖南省岳阳市', '湖南省常德市', '湖南省张家界市', '湖南省益阳市', '湖南省娄底市', '湖南省郴州市',
					'湖南省永州市', '湖南省怀化市', '广东省广州市', '广东省韶关市', '广东省深圳市', '广东省珠海市', '广东省汕头市', '广东省佛山市', '广东省江门市', '广东省湛江市',
					'广东省茂名市', '广东省肇庆市', '广东省惠州市', '广东省梅州市', '广东省汕尾市', '广东省河源市', '广东省阳江市', '广东省清远市', '广东省东莞市', '广东省中山市',
					'广东省潮州市', '广东省揭阳市', '广东省云浮市', '广西壮族自治区南宁市', '广西壮族自治区柳州市', '广西壮族自治区桂林市', '广西壮族自治区梧州市', '广西壮族自治区北海市',
					'广西壮族自治区崇左市', '广西壮族自治区来宾市', '广西壮族自治区贺州市', '广西壮族自治区玉林市', '广西壮族自治区百色市', '广西壮族自治区河池市', '广西壮族自治区钦州市',
					'广西壮族自治区防城港市', '广西壮族自治区贵港市', '海南省海口市', '海南省三亚市', '海南省三沙市', '海南省儋州市', '四川省成都市', '四川省绵阳市', '四川省自贡市',
					'四川省攀枝花市', '四川省泸州市', '四川省德阳市', '四川省广元市', '四川省遂宁市', '四川省内江市', '四川省乐山市', '四川省资阳市', '四川省宜宾市',
					'四川省南充市', '四川省达州市', '四川省雅安市', '四川省广安市', '四川省巴中市', '四川省眉山市', '贵州省贵阳市', '贵州省遵义市', '贵州省六盘水市',
					'贵州省安顺市', '贵州省毕节市', '贵州省铜仁市', '云南省昆明市', '云南省曲靖市', '云南省玉溪市', '云南省昭通市', '云南省保山市', '云南省丽江市', '云南省普洱市',
					'云南省临沧市', '西藏自治区拉萨市', '西藏自治区日喀则市', '西藏自治区昌都市', '西藏自治区林芝市', '西藏自治区山南市', '西藏自治区那曲市', '陕西省西安市',
					'陕西省宝鸡市', '陕西省咸阳市', '陕西省铜川市', '陕西省渭南市', '陕西省延安市', '陕西省榆林市', '陕西省汉中市', '陕西省安康市', '陕西省商洛市', '甘肃省兰州市',
					'甘肃省嘉峪关市', '甘肃省金昌市', '甘肃省白银市', '甘肃省天水市', '甘肃省武威市', '甘肃省张掖市', '甘肃省平凉市', '甘肃省酒泉市', '甘肃省庆阳市',
					'甘肃省定西市', '甘肃省陇南市', '青海省西宁市', '青海省海东市', '宁夏回族自治区银川市', '宁夏回族自治区石嘴山市', '宁夏回族自治区吴忠市', '宁夏回族自治区固原市',
					'宁夏回族自治区中卫市', '新疆维吾尔自治区乌鲁木齐市', '新疆维吾尔自治区克拉玛依市', '新疆维吾尔自治区吐鲁番市', '新疆维吾尔自治区哈密市'
				],
				expect_household_registration: ['河北省石家庄市', '河北省唐山市', '河北省秦皇岛市', '河北省邯郸市', '河北省邢台市', '河北省保定市', '河北省张家口市',
					'河北省承德市', '河北省沧州市', '河北省廊坊市', '河北省衡水市', '山西省太原市', '山西省大同市', '山西省朔州市', '山西省忻州市', '山西省阳泉市', '山西省吕梁市',
					'山西省晋中市', '山西省长治市', '山西省晋城市', '山西省临汾市', '山西省运城市', '内蒙古自治区呼和浩特市', '内蒙古自治区包头市', '内蒙古自治区乌海市',
					'内蒙古自治区赤峰市', '内蒙古自治区通辽市', '内蒙古自治区鄂尔多斯市', '内蒙古自治区呼伦贝尔市', '内蒙古自治区巴彦淖尔市', '内蒙古自治区乌兰察布市', '辽宁省沈阳市',
					'辽宁省大连市', '辽宁省鞍山市', '辽宁省抚顺市', '辽宁省本溪市', '辽宁省丹东市', '辽宁省锦州市', '辽宁省营口市', '辽宁省阜新市', '辽宁省辽阳市', '辽宁省盘锦市',
					'辽宁省铁岭市', '辽宁省朝阳市', '辽宁省葫芦岛市', '吉林省长春市', '吉林省吉林市', '吉林省四平市', '吉林省通化市', '吉林省白城市', '吉林省辽源市',
					'吉林省松原市', '吉林省白山市', '黑龙江省哈尔滨市', '黑龙江省齐齐哈尔市', '黑龙江省牡丹江市', '黑龙江省佳木斯市', '黑龙江省大庆市', '黑龙江省鸡西市',
					'黑龙江省双鸭山市', '黑龙江省伊春市', '黑龙江省七台河市', '黑龙江省鹤岗市', '黑龙江省黑河市', '黑龙江省绥化市', '江苏省南京市', '江苏省无锡市', '江苏省徐州市',
					'江苏省常州市', '江苏省苏州市', '江苏省南通市', '江苏省连云港市', '江苏省淮安市', '江苏省盐城市', '江苏省扬州市', '江苏省镇江市', '江苏省泰州市',
					'江苏省宿迁市', '浙江省杭州市', '浙江省宁波市', '浙江省温州市', '浙江省绍兴市', '浙江省湖州市', '浙江省嘉兴市', '浙江省金华市', '浙江省衢州市', '浙江省台州市',
					'浙江省丽水市', '浙江省舟山市', '安徽省合肥市', '安徽省芜湖市', '安徽省蚌埠市', '安徽省淮南市', '安徽省马鞍山市', '安徽省淮北市', '安徽省铜陵市',
					'安徽省安庆市', '安徽省黄山市', '安徽省阜阳市', '安徽省宿州市', '安徽省滁州市', '安徽省六安市', '安徽省宣城市', '安徽省池州市', '安徽省亳州市', '福建省福州市',
					'福建省厦门市', '福建省漳州市', '福建省泉州市', '福建省三明市', '福建省莆田市', '福建省南平市', '福建省龙岩市', '福建省宁德市', '江西省南昌市', '江西省九江市',
					'江西省上饶市', '江西省抚州市', '江西省宜春市', '江西省吉安市', '江西省赣州市', '江西省景德镇市', '江西省萍乡市', '江西省新余市', '江西省鹰潭市',
					'山东省济南市', '山东省青岛市', '山东省淄博市', '山东省枣庄市', '山东省东营市', '山东省烟台市', '山东省潍坊市', '山东省济宁市', '山东省泰安市', '山东省威海市',
					'山东省日照市', '山东省滨州市', '山东省德州市', '山东省聊城市', '山东省临沂市', '山东省菏泽市', '河南省郑州市', '河南省开封市', '河南省洛阳市',
					'河南省平顶山市', '河南省安阳市', '河南省鹤壁市', '河南省新乡市', '河南省焦作市', '河南省濮阳市', '河南省许昌市', '河南省漯河市', '河南省三门峡市',
					'河南省商丘市', '河南省周口市', '河南省驻马店市', '河南省南阳市', '河南省信阳市', '湖北省武汉市', '湖北省黄石市', '湖北省十堰市', '湖北省宜昌市',
					'湖北省襄阳市', '湖北省鄂州市', '湖北省荆门市', '湖北省孝感市', '湖北省荆州市', '湖北省黄冈市', '湖北省咸宁市', '湖北省随州市', '湖南省长沙市', '湖南省株洲市',
					'湖南省湘潭市', '湖南省衡阳市', '湖南省邵阳市', '湖南省岳阳市', '湖南省常德市', '湖南省张家界市', '湖南省益阳市', '湖南省娄底市', '湖南省郴州市',
					'湖南省永州市', '湖南省怀化市', '广东省广州市', '广东省韶关市', '广东省深圳市', '广东省珠海市', '广东省汕头市', '广东省佛山市', '广东省江门市', '广东省湛江市',
					'广东省茂名市', '广东省肇庆市', '广东省惠州市', '广东省梅州市', '广东省汕尾市', '广东省河源市', '广东省阳江市', '广东省清远市', '广东省东莞市', '广东省中山市',
					'广东省潮州市', '广东省揭阳市', '广东省云浮市', '广西壮族自治区南宁市', '广西壮族自治区柳州市', '广西壮族自治区桂林市', '广西壮族自治区梧州市', '广西壮族自治区北海市',
					'广西壮族自治区崇左市', '广西壮族自治区来宾市', '广西壮族自治区贺州市', '广西壮族自治区玉林市', '广西壮族自治区百色市', '广西壮族自治区河池市', '广西壮族自治区钦州市',
					'广西壮族自治区防城港市', '广西壮族自治区贵港市', '海南省海口市', '海南省三亚市', '海南省三沙市', '海南省儋州市', '四川省成都市', '四川省绵阳市', '四川省自贡市',
					'四川省攀枝花市', '四川省泸州市', '四川省德阳市', '四川省广元市', '四川省遂宁市', '四川省内江市', '四川省乐山市', '四川省资阳市', '四川省宜宾市',
					'四川省南充市', '四川省达州市', '四川省雅安市', '四川省广安市', '四川省巴中市', '四川省眉山市', '贵州省贵阳市', '贵州省遵义市', '贵州省六盘水市',
					'贵州省安顺市', '贵州省毕节市', '贵州省铜仁市', '云南省昆明市', '云南省曲靖市', '云南省玉溪市', '云南省昭通市', '云南省保山市', '云南省丽江市', '云南省普洱市',
					'云南省临沧市', '西藏自治区拉萨市', '西藏自治区日喀则市', '西藏自治区昌都市', '西藏自治区林芝市', '西藏自治区山南市', '西藏自治区那曲市', '陕西省西安市',
					'陕西省宝鸡市', '陕西省咸阳市', '陕西省铜川市', '陕西省渭南市', '陕西省延安市', '陕西省榆林市', '陕西省汉中市', '陕西省安康市', '陕西省商洛市', '甘肃省兰州市',
					'甘肃省嘉峪关市', '甘肃省金昌市', '甘肃省白银市', '甘肃省天水市', '甘肃省武威市', '甘肃省张掖市', '甘肃省平凉市', '甘肃省酒泉市', '甘肃省庆阳市',
					'甘肃省定西市', '甘肃省陇南市', '青海省西宁市', '青海省海东市', '宁夏回族自治区银川市', '宁夏回族自治区石嘴山市', '宁夏回族自治区吴忠市', '宁夏回族自治区固原市',
					'宁夏回族自治区中卫市', '新疆维吾尔自治区乌鲁木齐市', '新疆维吾尔自治区克拉玛依市', '新疆维吾尔自治区吐鲁番市', '新疆维吾尔自治区哈密市'
				],
				education_background_list: ["高中生", "大专", "本科", "硕士", "博士"],
				expect_education_background_list: ["高中生", "大专", "本科", "硕士", "博士"],
				salary_yearly_list: ["0-1万", "1-5万", "6-10万", "11-15万", "16-20万", "21-30万", "31-40万", "41-50万", "51-75万",
					"76-100万", "100-200万", "200-500万", ">500万"
				],
				expect_salary_yearly_list: ["0-1万", "1-5万", "6-10万", "11-15万", "16-20万", "21-30万", "31-40万", "41-50万",
					"51-75万", "76-100万", "100-200万", "200-500万", ">500万"
				],
				car_and_house_status_list: ["没有车和房且有贷款", "没有车和房但无贷款", "有车但有贷款", "有房但有贷款", "有车且没有贷款", "有房且没有贷款", "有车有房但有贷款",
					"有车有房且无贷款"
				],
				expect_car_and_house_status_list: ["没有车和房且有贷款", "没有车和房但无贷款", "有车但有贷款", "有房但有贷款", "有车且没有贷款", "有房且没有贷款",
					"有车有房但有贷款", "有车有房且无贷款"
				],
				myself_info_full: false,
				expect_info_full: false,
				save_myself_info_successful: false,
				save_expect_info_successful: false
			}
		},
		onLoad() {
			variable.c++;
			uni.request({
				url: this.$baseUrl + "PersonalInformation/findAll",
				method: "GET",
				success: (response) => {
					let that = this;
					// alert("PersonalInformationId:"+variable.id);
					variable.name = response.data.data[variable.id - 1].name;
					that.list_age[that.user_info.age] = response.data.data[variable.id - 1].age;
					that.user_info.name = response.data.data[variable.id - 1].name;
					that.gender_list[that.user_info.gender] = response.data.data[variable.id - 1].gender;
					variable.gender = that.gender_list[that.user_info.gender];
					that.my_household_registration[that.user_info.household_registration] = response.data.data[
						variable.id - 1].household_registration;
					that.user_info.hobby = response.data.data[variable.id - 1].hobby;
					that.education_background_list[that.user_info.education_background] = response.data.data[
						variable.id - 1].education_background;
					that.user_info.occupation = response.data.data[variable.id - 1].occupation;
					that.salary_yearly_list[that.user_info.salary_yearly] = response.data.data[variable.id - 1]
						.salary_yearly;
					that.car_and_house_status_list[that.user_info.car_and_house_status] = response.data.data[
						variable.id - 1].car_and_house_status;
					that.user_info.simple_introduction = response.data.data[variable.id - 1]
						.simple_introduction;
				}
			});
			// http://localhost:54920/ExpectOtherInformation/findAll
			uni.request({
				url: this.$baseUrl + "ExpectOtherInformation/findAll",
				method: "GET",
				success: (response) => {
					let that = this;
					// alert("ExpectOtherInformationId:"+variable.id);
					that.expect_age[that.expect_people_info.age] = response.data.data[variable.id - 1].age;
					that.expect_household_registration[that.expect_people_info.household_registration] =
						response.data.data[variable.id - 1].household_registration;
					variable.expect_household_registration = response.data.data[variable.id - 1]
						.household_registration;
					that.expect_people_info.hobby = response.data.data[variable.id - 1].hobby;
					that.expect_education_background_list[that.expect_people_info.education_background] =
						response.data.data[variable.id - 1].education_background;
					that.expect_people_info.occupation = response.data.data[variable.id - 1].occupation;
					that.expect_salary_yearly_list[that.expect_people_info.salary_yearly] = response.data.data[
						variable.id - 1].salary_yearly;
					that.expect_car_and_house_status_list[that.expect_people_info.car_and_house_status] =
						response.data.data[variable.id - 1].car_and_house_status;
				}
			});
		},
		methods: {
			bindAgeChange: function(e) {
				this.user_info.age = e.detail.value;
			},
			bindGenderChange: function(e) {
				this.user_info.gender = e.detail.value;
			},
			bindHouseholdRegistrationChange: function(e) {
				this.user_info.household_registration = e.detail.value;
			},
			bindEducationBackgroundChange: function(e) {
				this.user_info.education_background = e.detail.value;
			},
			bindSalaryYearlyChange: function(e) {
				this.user_info.salary_yearly = e.detail.value;
			},
			bindCarAndHouseStatusChange: function(e) {
				this.user_info.car_and_house_status = e.detail.value;
			},
			bindTextAreaBlur: function(e) {
				this.user_info.simple_introduction = e.detail.value;
			},
			bindExpectAgeChange: function(e) {
				this.expect_people_info.age = e.detail.value;
			},
			bindExpectHouseholdRegistrationChange: function(e) {
				this.expect_people_info.household_registration = e.detail.value;
			},
			bindExpectEducationBackgroundChange: function(e) {
				this.expect_people_info.education_background = e.detail.value;
			},
			bindExpectSalaryYearlyChange: function(e) {
				this.expect_people_info.salary_yearly = e.detail.value;
			},
			bindExpectCarAndHouseStatusChange: function(e) {
				this.expect_people_info.car_and_house_status = e.detail.value;
			},
			save() {
				if (this.list_age[this.user_info.age] == "") {
					uni.showToast({
						title: "年龄不能为空",
						icon: "error"
					});
				} else if (this.user_info.name == "") {
					uni.showToast({
						title: "姓名不能为空",
						icon: "error"
					});
				} else if (this.gender_list[this.user_info.gender] == "") {
					uni.showToast({
						title: "性别不能为空",
						icon: "error"
					});
				} else if (this.my_household_registration[this.user_info.household_registration] == "") {
					uni.showToast({
						title: "户籍不能为空",
						icon: "error"
					});
				} else if (this.user_info.hobby == "") {
					uni.showToast({
						title: "爱好不能为空(没有写无即可)",
						icon: "error"
					});
				} else if (this.education_background_list[this.user_info.education_background] == "") {
					uni.showToast({
						title: "学历不能为空",
						icon: "error"
					});
				} else if (this.user_info.occupation == "") {
					uni.showToast({
						title: "工作不能为空(没有写无即可)",
						icon: "error"
					});
				} else if (this.salary_yearly_list[this.user_info.salary_yearly] == "") {
					uni.showToast({
						title: "年薪不能为空",
						icon: "error"
					});
				} else if (this.car_and_house_status_list[this.user_info.car_and_house_status] == "") {
					uni.showToast({
						title: "车房不能为空",
						icon: "error"
					});
				} else if (this.user_info.simple_introduction == "") {
					uni.showToast({
						title: "简介不能为空",
						icon: "error"
					});
				} else {
					this.myself_info_full = true;
				}
				if (this.expect_age[this.expect_people_info.age] == "") {
					uni.showToast({
						title: "期望配偶的年龄不能为空",
						icon: "error"
					});
				} else if (this.expect_household_registration[this.expect_people_info.household_registration] == "") {
					uni.showToast({
						title: "期望配偶的户籍不能为空",
						icon: "error"
					});
				} else if (this.expect_education_background_list[this.expect_people_info.education_background] == "") {
					uni.showToast({
						title: "期望配偶的学历不能为空",
						icon: "error"
					});
				} else if (this.expect_people_info.hobby == "") {
					uni.showToast({
						title: "期望配偶的爱好不能为空(没有写无即可)",
						icon: "error"
					});
				} else if (this.expect_people_info.occupation == "") {
					uni.showToast({
						title: "期望配偶的工作不能为空(没有写无即可)",
						icon: "error"
					});
				} else if (this.expect_salary_yearly_list[this.expect_people_info.expect_salary_yearly_list] == "") {
					uni.showToast({
						title: "期望配偶的年薪不能为空",
						icon: "error"
					});
				} else if (this.expect_people_info[this.expect_people_info.car_and_house_status] == "") {
					uni.showToast({
						title: "期望配偶的车房不能为空",
						icon: "error"
					});
				} else {
					this.expect_info_full = true;
				}
				if (this.myself_info_full) {
					uni.request({
						url: this.$baseUrl + "PersonalInformation/addOrMod",
						method: "POST",
						data: {
							id: variable.id,
							head_image: variable.head_image,
							personal_id: variable.personal_id,
							nick_name: variable.account,
							name: this.user_info.name,
							age: this.list_age[this.user_info.age],
							gender: this.gender_list[this.user_info.gender],
							household_registration: this.my_household_registration[this.user_info
								.household_registration],
							hobby: this.user_info.hobby,
							education_background: this.education_background_list[this.user_info
								.education_background],
							occupation: this.user_info.occupation,
							salary_yearly: this.salary_yearly_list[this.user_info.salary_yearly],
							car_and_house_status: this.car_and_house_status_list[this.user_info
								.car_and_house_status],
							simple_introduction: this.user_info.simple_introduction
						},
						success: (response) => {
							// this指向改变导致不能修改到data里面的值
							// 将this暂时保存
							let that = this;
							that.save_myself_info_successful = true;
							console.log(response);
						},
						fail: (response) => {
							let that = this;
							that.save_myself_info_successful = false;
							console.log(response);
							// alert("保存失败!");
							uni.showToast({
								title: "保存失败!",
								icon: "error"
							});
						}
					});
				}
				if (this.expect_info_full) {
					uni.request({
						url: this.$baseUrl + "ExpectOtherInformation/addOrMod",
						method: "POST",
						data: {
							id: variable.id,
							request_people: this.user_info.name,
							age: this.expect_age[this.expect_people_info.age],
							household_registration: this.expect_household_registration[this.expect_people_info
								.household_registration],
							hobby: this.expect_people_info.hobby,
							education_background: this.expect_education_background_list[this.expect_people_info
								.education_background],
							occupation: this.expect_people_info.occupation,
							salary_yearly: this.expect_salary_yearly_list[this.expect_people_info.salary_yearly],
							car_and_house_status: this.expect_car_and_house_status_list[this.expect_people_info
								.car_and_house_status],
						},
						success: (response) => {
							let that = this;
							that.save_expect_info_successful = true;
							console.log(response);
							// alert("保存成功!");
							uni.showToast({
								title: "保存成功!",
								icon: "success"
							});
						},
						fail: (response) => {
							let that = this;
							that.save_expect_info_successful = false;
							console.log(response);
							// alert("保存失败!");
							uni.showToast({
								title: "保存失败!",
								icon: "error"
							});
						}
					});
				}
				// if(this.save_expect_info_successful && this.save_expect_info_successful){
				uni.navigateTo({
					url: "/pages/myself/myself?name=" + this.user_info.name
				});
				// }
			}
		}
	}
</script>

<style>
	.image {
		position: absolute;
		z-index: -1;
		width: 100%;
		background-color: rgb(255, 170, 255);
		height: 3500px;
		top: 0px;
	}

	.name .name-input {
		position: relative;
		width: 70%;
		height: 50px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.age .age-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 40px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.gender .gender-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 80px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.household-registration .household-registration-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 120px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.hobby .hobby-input {
		position: relative;
		width: 70%;
		height: 50px;
		top: 160px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.education-background .education-background-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 200px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.occupation .occupation-input {
		position: relative;
		width: 70%;
		height: 50px;
		top: 240px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.salary-yearly .salary-yearly-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 280px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.car-and-house-status .car-and-house-status-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 320px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.simple-introduction .simple-introduction-input {
		position: relative;
		width: 70%;
		height: 50px;
		top: 360px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-age .expect-age-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 440px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-household-registration .expect-household-registration-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 480px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-hobby .expect-hobby-input {
		position: relative;
		width: 70%;
		height: 50px;
		top: 520px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-education-background .expect-education-background-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 560px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-occupation .expect-occupation-input {
		position: relative;
		width: 70%;
		height: 50px;
		top: 600px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-salary-yearly .expect-salary-yearly-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 640px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.expect-car-and-house-status .expect-car-and-house-status-picker {
		position: relative;
		width: 70%;
		height: 50px;
		top: 680px;
		left: 23%;
		border: blue 1px solid;
		background-color: aqua;
	}

	.personal-information {
		position: relative;
		left: 35%;
	}

	.expect-other-information {
		position: relative;
		left: 28%;
		top: 440px;
	}

	.name-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 40px;
	}

	.age-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 80px;
	}

	.gender-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 120px;
	}

	.education-background-picker {
		text-align: center;
	}

	.household-registration-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 160px;
	}

	.hobby-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 200px;
	}

	.education-background-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 240px;
	}

	.occupation-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 280px;
	}

	.salary-yearly-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 320px;
	}

	.car-and-house-status-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 360px;
	}

	.simple-introduction-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 400px;
	}

	.expect-age-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 480px;
	}

	.expect-household-registration-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 520px;
	}

	.expect-hobby-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 560px;
	}

	.expect-education-background-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 600px;
	}

	.expect-occupation-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 640px;
	}

	.expect-salary-yearly-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 680px;
	}

	.expect-car-and-house-status-text {
		position: relative;
		font-size: 30px;
		left: 5%;
		top: 720px;
	}

	.uni-input {
		text-align: ceter;
		font-size: 38px;
	}

	.uni-input2 {
		text-align: center;
		font-size: 32px;
	}

	.age-picker {
		text-align: center;
	}

	.gender-picker {
		text-align: center;
	}

	.name-input {
		font-size: 35px;
	}

	.hobby-input {
		font-size: 25px;
	}

	.household-registration-picker {
		text-align: center;
	}

	.salary-yearly-picker {
		text-align: center;
	}

	.car-and-house-status-picker {
		text-align: center;
	}

	.occupation-input {
		font-size: 25px;
	}

	.simple-introduction-input {
		font-size: 35px;
	}

	.expect-age-picker {
		text-align: center;
	}

	.expect-hobby-input {
		font-size: 20px;
	}

	.expect-household-registration-picker {
		text-align: center;
	}

	.expect-car-and-house-status-picker {
		text-align: center;
	}

	.expect-education-background-picker {
		text-align: center;
	}

	.expect-salary-yearly-picker {
		text-align: center;
	}

	.expect-occupation-input {
		font-size: 20px;
	}

	.expect-car-and-house-status-text {
		font-size: 30px;
	}

	.save-btn {
		position: relative;
		width: 80%;
		top: 750px;
		background-color: green;
	}
</style>