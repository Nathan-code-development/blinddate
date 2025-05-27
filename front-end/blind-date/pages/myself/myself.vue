<template>
	<view class="content">
		<view class="header">
			<image :src="user_info.head_image" class="head-image" @click="uploadAndDownloadHeadImage"></image>
			<p class="account-name">{{nick_name}}</p>
			<p class="account-id">ID:{{personal_id}}</p>
			<button class="edit-personal-informtion-btn" @click="editPersonalInformation">编辑个人资料</button>
		</view>
		<view class="personal-information-panel">
			<h1 class="personal-information-header">个人信息</h1>
			<p class="p-text">性别: {{user_info.gender}}</p>
			<p class="p-text">名字: {{user_info.name}}</p>
			<p class="p-text">年龄: {{user_info.age}}</p>
			<p class="p-text">户籍: {{user_info.household_registration}}</p>
			<p class="p-text">爱好: {{user_info.hobby}}</p>
			<p class="p-text">学历: {{user_info.education_background}}</p>
			<p class="p-text">工作: {{user_info.occupation}}</p>
			<p class="p-text">年薪: {{user_info.salary_yearly}}</p>
			<p class="p-text">车房: {{user_info.car_and_house_status}}</p>
			<p class="p-text">简单描述(自我介绍): {{user_info.simple_introduction}}</p>
		</view>
		<view class="expect-another-information-panel">
			<h1 class="expect-other-information-header">个人择偶意向</h1>
			<p class="p-text">年龄: {{expect_people_info.age}}</p>
			<p class="p-text">户籍: {{expect_people_info.household_registration}}</p>
			<p class="p-text">爱好: {{expect_people_info.hobby}}</p>
			<p class="p-text">学历: {{expect_people_info.education_background}}</p>
			<p class="p-text">工作: {{expect_people_info.occupation}}</p>
			<p class="p-text">年薪: {{expect_people_info.salary_yearly}}</p>
			<p class="p-text">车房: {{expect_people_info.car_and_house_status}}</p>
		</view>
		<view class="about-our">
			<button class="about-our-btn" @click="goToAboutOurPage">关于我们</button>
		</view>
		<view class="exit-login">
			<button class="exit-login-btn" @click="exitLogin">退出登录</button>
		</view>
		<view class="version">
			<p>版权:1.0.0 若想联系开发者,可发送信息到开发者的邮件,邮件为nls1175235190@qq.com。开发者的电话号码为:13388421389</p>
		</view>
		<view>
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
			<br />
		</view>
		<view class="footer">
			<myFooter></myFooter>
		</view>
	</view>
</template>

<script>
	import {
		fromJSON
	} from "tough-cookie";
	import variable from "../../common/help.js";
	import myFooter from "../footer/footer.vue";
	export default {
		components: {
			myFooter
		},
		data() {
			return {
				nick_name: "",
				img_file_name: "",
				json_data: {},
				name: "",
				personal_id: 0,
				hasGenerateId: false,
				ID: 0,
				baseUrl: "http://121.40.144.4:54920/PersonalInformation/download?name=",
				user_info: {
					head_image: "../../static/head_image.png", //头像
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
					age: "16-24", //年龄
					household_registration: "", //户籍
					hobby: "", //爱好
					education_background: "", //学历
					occupation: "", //工作
					salary_yearly: "", //年薪
					car_and_house_status: "" //车房
				},
				expect_people_info_list: []
			}
		},
		onLoad(option) {
			let that = this;
			this.nick_name = variable.account;
			variable.nick_name = this.nick_name;
			this.ID = variable.id;
			if (variable.c >= 1) {
				this.name = variable.name;
			} else {
				this.name = option.name;
			}
			uni.request({
				url: this.$baseUrl + "PersonalInformation/findAll",
				method: "GET",
				success: (res) => {
					if (res.data.data.length == 0) {
						that.ID = 1;
						variable.id = that.ID;
						variable.personal_id = that.personal_id;
					} else {
						for (var i = 0; i < res.data.data.length; i++) {
							if (that.nick_name == res.data.data[i].nick_name) {
								that.personal_id = res.data.data[i].personal_id;
								that.ID = i + 1;
								variable.personal_id = that.personal_id;
							}
						}
					}
				}
			});
			this.getGenerateId();
			uni.request({
				url: this.$baseUrl + "PersonalInformation/findAll",
				method: "GET",
				success: (response) => {
					// console.log(response);
					for (var i = 0; i < response.data.data.length; i++) {
						if (that.nick_name == response.data.data[i].nick_name) {
							if (response.data.data[i].head_image != "" || response.data.data[i].head_image !=
								null) {
								that.user_info.head_image = response.data.data[i].head_image;
								variable.head_image = response.data.data[i].head_image;
							} else {
								that.user_info.head_image = "../../static/head_image.png";
								variable.head_image = "../../static/head_image.png";
							}
							that.user_info.gender = response.data.data[i].gender;
							variable.gender = that.user_info.gender;
							that.user_info.name = response.data.data[i].name;
							that.name = response.data.data[i].name;
							that.user_info.age = response.data.data[i].age;
							that.user_info.household_registration = response.data.data[i]
								.household_registration;
							that.user_info.hobby = response.data.data[i].hobby;
							that.user_info.education_background = response.data.data[i].education_background;
							that.user_info.occupation = response.data.data[i].occupation;
							that.user_info.salary_yearly = response.data.data[i].salary_yearly;
							that.user_info.car_and_house_status = response.data.data[i].car_and_house_status;
							that.user_info.simple_introduction = response.data.data[i].simple_introduction;
							that.$forceUpdate();
						}
					}
				}
			});
			this.$forceUpdate();
			that.getExpectOtherInformation().then((res) => {
				console.log(res);
				for (var i = 0; i < res.data.data.length; i++) {
					if (that.name == res.data.data[i].request_people) {
						that.expect_people_info.age = res.data.data[i].age;
						that.expect_people_info.household_registration = res.data.data[i].household_registration;
						that.expect_people_info.hobby = res.data.data[i].hobby;
						that.expect_people_info.education_background = res.data.data[i].education_background;
						that.expect_people_info.occupation = res.data.data[i].occupation;
						that.expect_people_info.salary_yearly = res.data.data[i].salary_yearly;
						that.expect_people_info.car_and_house_status = res.data.data[i].car_and_house_status;
						variable.age = that.expect_people_info.age;
						variable.household_registration = that.expect_people_info.household_registration;
						variable.hobby = that.expect_people_info.hobby;
						variable.education_background = that.expect_people_info.education_background;
						variable.occupation = that.expect_people_info.occupation;
						variable.salary_yearly = that.expect_people_info.salary_yearly;
						variable.car_and_house_status = that.expect_people_info.car_and_house_status;
						that.$forceUpdate();
					}
				}
			}).catch((error) => {
				console.log(error);
			});
		},
		methods: {
			uploadAndDownloadHeadImage() {
				let that = this;
				uni.chooseImage({
					count: 1,
					// original 原图，compressed 压缩图，默认二者都有
					sizeType: ['original', 'compressed'],
					// album 从相册选图，camera 使用相机，默认二者都有
					sourceType: ['album', 'camera'],
					success: (chooseImageRes) => {
						console.log(chooseImageRes);
						if (chooseImageRes.tempFilePaths.length > 0) {
							let filePath = chooseImageRes.tempFilePaths;
							// 进行上传操作
							console.log(filePath[0]);
							// uni.compressImage({
							// 	src: filePath[0], // 图片路径
							// 	quality: 50, // 压缩质量，范围为0 - 100，100为不压缩
							// 	success: function(compressRes) {
							// 		console.log(compressRes.tempFilePath);
							// 		// 上传图片到服务器
							// 		uni.uploadFile({
							// 			url: that.$baseUrl + "PersonalInformation/upload",
							// 			filePath: compressRes.tempFilePath,
							// 			name: "file",
							// 			success: (res) => {
							// 				console.log(res);
							// 				that.json_data = JSON.parse(res.data);
							// 				that.img_file_name = that.json_data.content;
							// 				console.log("img_file_name:" + that.img_file_name);
							// 				variable.head_image = that.baseUrl + that.img_file_name;
							// 				that.user_info.head_image = variable.head_image;
							// 				uni.request({
							// 					url: that.$baseUrl + "PersonalInformation/addHeadImage?id=" + that.ID + "&head_image=" + that.img_file_name,
							// 					method: "GET",
							// 					success: (response) => {
							// 						console.log(response);
							// 						that.$forceUpdate();
							// 					}
							// 				});
							// 			}
							// 		});
							// 	},
							// 	fail: function(compressErr) {
							// 		console.log('compress fail:', compressErr);
							// 	}
							// });
							// 上传图片到服务器
							uni.uploadFile({
								url: that.$baseUrl + "PersonalInformation/upload",
								filePath: filePath[0],
								name: "file",
								success: (res) => {
									console.log(res);
									that.json_data = JSON.parse(res.data);
									that.img_file_name = that.json_data.content;
									console.log("img_file_name:" + that.img_file_name);
									variable.head_image = that.$baseUrl + that.img_file_name;
									that.user_info.head_image = variable.head_image;
									uni.request({
										url: that.$baseUrl + "PersonalInformation/addHeadImage?id=" + that.ID + "&head_image=" + that.img_file_name,
										method: "GET",
										success: (response) => {
											console.log(response);
											that.$forceUpdate();
										}
									});
								}
							});
							uni.downloadFile({
								url: this.$baseUrl + "PersonalInformation/download?name=" + that.img_file_name,
								success: (res) => {
									console.log(res);
									that.$forceUpdate();
								}
							});
						}
					}
				});
			},
			async getExpectOtherInformation() {
				return new Promise(((resolve, reject) => {
					uni.request({
						url: this.$baseUrl + "ExpectOtherInformation/findAll",
						method: "GET",
						success: (response) => {
							resolve(response);
						},
						fail: (error) => {
							reject(error);
						}
					});
				}));
			},
			editPersonalInformation() {
				uni.navigateTo({
					url: "/pages/personalInformation/personalInformation"
				})
			},
			getGenerateId() {
				let that = this;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/hasGenerateId?id=" + this.ID,
					method: "GET",
					success: (res) => {
						that.hasGenerateId = Boolean(res.data);
						uni.showToast({
							title: that.hasGenerateId,
							icon: "success"
						});
						that.$forceUpdate();
						uni.request({
							url: this.$baseUrl + "PersonalInformation/generateID",
							method: "GET",
							success: (response) => {
								console.log(response);
								if (that.hasGenerateId) {
									variable.personal_id = that.personal_id;
								} else {
									that.personal_id = response.data;
									variable.personal_id = that.personal_id;
									uni.request({
										url: this.$baseUrl +
											"PersonalInformation/addGenerateId?personal_id=" +
											that.personal_id + "&id=" + this.ID,
										method: "GET",
										success: (response2) => {
											console.log(response2);
										}
									});
								}
							}
						});
					}
				});
				if (this.hasGenerateId) {
					uni.request({
						url: this.$baseUrl + "PersonalInformation/addOrMod",
						method: "POST",
						data: {
							id: variable.id,
							head_image: variable.head_image,
							personal_id: this.personal_id,
							nick_name: variable.account,
							name: variable.name,
							age: variable.age,
							gender: variable.gender,
							household_registration: variable.household_registration,
							hobby: variable.hobby,
							education_background: variable.education_background,
							occupation: variable.occupation,
							salary_yearly: variable.salary_yearly,
							car_and_house_status: variable.car_and_house_status,
							simple_introduction: variable.simple_introduction
						},
						success: (response) => {
							console.log(response);
						},
						fail: (response) => {
							console.log(response);
						}
					});
					uni.request({
						url: this.$baseUrl + "ExpectOtherInformation/addOrMod",
						method: "POST",
						data: {
							id: variable.id,
							request_people: this.user_info.name,
							age: this.expect_people_info.age,
							household_registration: this.expect_people_info.household_registration,
							hobby: this.expect_people_info.hobby,
							education_background: this.expect_people_info.education_background,
							occupation: this.expect_people_info.occupation,
							salary_yearly: this.expect_people_info.salary_yearly,
							car_and_house_status: this.expect_people_info.car_and_house_status
						},
						success: (response) => {
							console.log(response);
						},
						fail: (response) => {
							console.log(response);
						}
					});
				}
			},
			exitLogin() {
				// alert("你已退出登录!");
				uni.showToast({
					title: "你已退出登录!",
					icon: "success"
				});
				uni.navigateTo({
					url: "/pages/index/index"
				});
			},
			goToAboutOurPage() {
				uni.navigateTo({
					url: "/pages/aboutOur/aboutOur"
				});
			}
		}
	}
</script>

<style>
	.header {
		position: relative;
		background-color: hotpink;
	}

	.head-image {
		position: relative;
		left: 7%;
		top: 20px;
		width: 23%;
		height: 85px;
		border-radius: 100px;
	}

	.account-name {
		position: relative;
		top: -65px;
		left: 32%;
		font-size: 20px;
	}

	.account-id {
		position: relative;
		top: -45px;
		left: 32%;
		font-size: 20px;
	}

	.edit-personal-informtion-btn {
		width: 40%;
		float: right;
		top: -85px;
		right: 2%;
	}

	.personal-information-panel {
		background-color: pink;
		position: relative;
		width: 90%;
		left: 5%;
		top: 30px;
		border-radius: 20px;
	}

	.expect-another-information-panel {
		background-color: pink;
		position: relative;
		width: 90%;
		left: 5%;
		top: 60px;
		border-radius: 20px;
	}

	.about-our .about-our-btn {
		width: 90%;
		position: relative;
		top: 80px;
		background-color: aqua;
	}

	.exit-login .exit-login-btn {
		width: 90%;
		position: relative;
		top: 100px;
		background-color: green;
	}

	.personal-information-header {
		position: relative;
		left: 35%;
		top: 10px;
		color: white;
	}

	.version {
		position: relative;
		font-size: 20px;
		top: 100px;
		left: 5%;
		width: 90%;
	}

	.p-text {
		position: relative;
		left: 5%;
		font-size: 25px;
		width: 90%;
		color: white;
	}

	.expect-other-information-header {
		position: relative;
		left: 25%;
		top: 10px;
		color: white;
	}

	.footer {
		position: fixed;
		bottom: 0;
		width: 100%;
	}
</style>