<template>
	<view class="content">
		<view class="header">
			<h1 class="details">详请</h1>
		</view>
		<view class="details-and-add-friend-panel">
			<h1 class="details-and-add-friend-header">个人信息</h1>
			<image :src="user_info.head_image" class="head-image"></image>
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
		<view class="add-friend-panel">
			<button class="return-page-btn" @click="returnPage">返回</button>
			<button class="add-friend-btn" @click="addNewFriend">+加好友</button>
		</view>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				user_info: {
					head_image: "../../static/head_image.png", //头像
					personal_id: "0",
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
				hasNotVerifyInformation: false,
				ID: 0,
				my_personal_id: "0",
				nick_name: "",
				friend_valid: "等待验证"
			}
		},
		onLoad: function(option) {
			this.nick_name = option.nick_name;
			this.my_personal_id = variable.personal_id;
			this.getInformation();
		},
		methods: {
			getInformation() {
				let that = this;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/findAll",
					method: "GET",
					success: (response) => {
						console.log(response);
						for (var i = 0; i < response.data.data.length; i++) {
							if (that.nick_name == response.data.data[i].nick_name) {
								if (response.data.data[i].head_image != "" || response.data.data[i]
									.head_image != null) {
									that.user_info.head_image = response.data.data[i].head_image;
									variable.head_image = response.data.data[i].head_image;
								} else {
									that.user_info.head_image = "../../static/head_image.png";
									variable.head_image = "../../static/head_image.png";
								}
								that.user_info.gender = response.data.data[i].gender;
								that.user_info.personal_id = response.data.data[i].personal_id;
								that.user_info.name = response.data.data[i].name;
								that.user_info.age = response.data.data[i].age;
								that.user_info.household_registration = response.data.data[i]
									.household_registration;
								that.user_info.hobby = response.data.data[i].hobby;
								that.user_info.education_background = response.data.data[i]
									.education_background;
								that.user_info.occupation = response.data.data[i].occupation;
								that.user_info.salary_yearly = response.data.data[i].salary_yearly;
								that.user_info.car_and_house_status = response.data.data[i]
									.car_and_house_status;
								that.user_info.simple_introduction = response.data.data[i].simple_introduction;
								that.$forceUpdate();
							}
						}
					}
				});
			},
			returnPage() {
				uni.navigateBack();
			},
			addNewFriend() {
				let that = this;
				uni.request({
					url: this.$baseUrl + "FriendRequest/findAll",
					method: "GET",
					success: (response) => {
						if (response.data.data.length != 0) {
							for (var i = 0; i < response.data.data.length; i++) {
								if (((that.user_info.personal_id == response.data.data[i].receive_people_id &&
											that.my_personal_id == response.data.data[i].request_people_id) ||
										(that.my_personal_id == response.data.data[i].receive_people_id &&
											that.user_info.personal_id == response.data.data[i]
											.request_people_id)) &&
									(response.data.data[i].status == "等待验证" || response.data.data[i].status ==
										"验证通过")) {
									that.hasNotVerifyInformation = true;
								}
							}
						} else {
							that.hasNotVerifyInformation = false;
						}
						if (that.hasNotVerifyInformation) {
							// alert("不能重复添加好友!");
							uni.showToast({
								title: "不能重复添加好友!",
								icon: "error"
							});
						} else {
							uni.request({
								url: this.$baseUrl + "FriendRequest/add",
								method: "POST",
								data: {
									request_people_id: that.my_personal_id,
									receive_people_id: that.user_info.personal_id,
									status: that.friend_valid
								},
								success: (res) => {
									console.log(res);
									// alert("申请成功,请等待好友完成验证!");
									uni.showToast({
										title: "申请成功,请等待好友完成验证!",
										icon: "success"
									});
								}
							});
						}
					}
				});
			}
		}
	}
</script>

<style>
	.head-image {
		position: relative;
		left: 40%;
		top: 25px;
		width: 25%;
		height: 80px;
		border-radius: 100px;
	}

	.header .details {
		position: relative;
		left: 45%;
	}

	.details-and-add-friend-panel {
		background-color: pink;
		position: relative;
		width: 90%;
		left: 5%;
		top: 30px;
		border-radius: 20px;
	}

	.details-and-add-friend-header {
		position: relative;
		left: 35%;
		top: 10px;
		color: white;
	}

	.p-text {
		position: relative;
		left: 5%;
		font-size: 22px;
		width: 90%;
		color: white;
	}

	.add-friend-panel .add-friend-btn {
		width: 90%;
		position: fixed;
		bottom: 10px;
		left: 5%;
		color: white;
		background-color: green;
		text-align: center;
	}

	.return-page-btn {
		width: 90%;
		position: fixed;
		bottom: 70px;
		left: 5%;
		color: white;
		background-color: blue;
		text-align: center;
	}
</style>