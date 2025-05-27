<template>
	<view>
		<view class="panel">
			<view class="login-text">
				<H1>注册</H1>
			</view>
			<view class="account-input">
				<input type="text" v-model="account" placeholder="请输入用户名:" class="account" />
			</view>
			<view class="password-input">
				<input type="safe-password" :password="true" v-model="password" placeholder="请输入密码:" class="password" />
			</view>
			<view class="phone-number-input">
				<input type="text" v-model="phoneNumber" placeholder="请输入手机号:" class="phone-number" />
			</view>
			<view class="code-input">
				<input type="safe-password" v-model="code" placeholder="请输入验证码:" class="code" />
			</view>
			<view class="code-btn">
				<button class="btn" @click="getCode" :disabled="disable">{{verifyButtonLabel}}</button>
			</view>
			<view class="register-btn">
				<button class="btn" @click="register">注册</button>
			</view>
		</view>
		<image src="../../static/index_picture.png"></image>
	</view>
</template>

<script>
	import sendVerificationCode from '@/js_sdk/tencentcloud-plugin-sms/send-verification-code.js';
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				account: "",
				user_info_list: [],
				password: "",
				phoneNumber: "",
				code: "",
				time: 60,
				timer: null,
				disable: false,
				verifyButtonLabel: "获取验证码",
				verifyCode: "verifyCode",
				ID: 0,
				isValid: false
			}
		},
		onLoad() {
			uni.request({
				url: this.$baseUrl + "login/findAll",
				method: "GET",
				success: (response) => {
					this.user_info_list = response.data.data;
					for(var i = 0;i < response.data.data.length;i++){
						if(this.account == response.data.data[i].account){
							this.ID = i + 1;
						}
					}
				}
			});
		},
		onUnload() {
			clearInterval(this.timer);
			this.timer = null;
		},
		methods: {
			getCode(){
				uni.request({
					url:  this.$baseUrl + "sms/smsXxs?phone=" + this.phoneNumber,
					method: "GET",
					success: (response) =>{
						if(this.phoneNumber.length != 11){
							// alert("请输入正确的手机号码");
							uni.showToast({
								title: "请输入正确的手机号码",
								icon: "error"
							});
						}else{
							this.verifyCode = response.data.verifyCode;
							// alert(this.verifyCode);
							// alert("验证码发送成功,请在手机查看!");
							uni.showToast({
								title: "验证码发送成功,请在手机查看",
								icon: "success"
							});
						}
						
					}
				});
				this.time = 60;
				if(this.phoneNumber.length != 11){
					uni.showToast({
						title: '手机号为11位!',
						icon: "error"
					});
				}else{
					this.verifyButtonLabel = "获取验证码(" + this.time + ")";
					this.disable = true;
					this.timer = setInterval(() => {
						if(this.time > 0){
							this.time -= 1;
							this.verifyButtonLabel = "获取验证码(" + this.time + ")";
						}else{
							this.time = 0;
							this.verifyButtonLabel = "获取验证码";
						}
						if(this.time == 0){
							clearInterval(this.timer);
							this.timer = null;
							this.disable = false;
							this.verifyButtonLabel = "获取验证码";
						}
					}, 1000);
				}
			},
			register() {
				let that = this;
				if(this.account != "" && this.password != "" && this.phoneNumber != ""){
					if(this.account.length >=10){
						// alert("用户名不能超过10位");
						uni.showToast({
							title: "用户名不能超过10位",
							icon: "error"
						});
					}else if(this.password.length < 6){
						// alert("密码不少于6位");
						uni.showToast({
							title: "密码不少于6位",
							icon: "error"
						});
					}else if(this.password.length > 25){
						// alert("密码最多不超过25位");
						uni.showToast({
							title: "密码最多不超过25位",
							icon: "error"
						});
					}else if(this.phoneNumber.length != 11){
						// alert("请输入正确的手机号码");
						uni.showToast({
							title: "请输入正确的手机号码",
							icon: "error"
						});
					}else if(this.verifyCode != this.code){
						// alert("验证码不正确!");
						uni.showToast({
							title: "验证码不正确!",
							icon: "error"
						});
					}else{
						if(this.user_info_list.length == 0){
							this.isValid = true;
						}
						for(var i = 0;i < this.user_info_list.length;i++){
							if(this.account == this.user_info_list[i].account){
								uni.showToast({
									title: "用户名已存在!",
									icon: "error"
								});
								break;
							}else{
								if(i == this.user_info_list.length - 1 && this.account != this.user_info_list.account){
									this.isValid = true;
								}
							}
						}
					}
				}else{
					// alert("请输入账号和密码和手机号!");
					uni.showToast({
						title: "请输入账号和密码和手机号!",
						icon: "error"
					});
				}
				if(this.isValid){
					variable.account = this.account;
					uni.request({
						url: this.$baseUrl + "PersonalInformation/generateID",
						method: "GET",
						success: (response) => {
							console.log(response);
							that.personal_id = response.data;
							variable.personal_id = that.personal_id;
						}
					});
					uni.request({
						url: this.$baseUrl + "login/add",
						method: "POST",
						data: {
							account: this.account,
							password: this.password
						},
						success: (response2) => {
							console.log(response2);
							// alert("注册成功!");
							uni.showToast({
								title: "注册成功!",
								icon: "success"
							});
							uni.request({
								url: this.$baseUrl + "PersonalInformation/addOrMod",
								method: "POST",
								data:{
									id: this.ID,
									head_image: variable.head_image,
									personal_id: variable.personal_id,
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
								success: (response2) => {
									console.log(response2);
								}
							});
							uni.navigateTo({
								url: "/pages/index/index"
							});
						},
						fail: (response2) => {
							console.log(response2);
							// alert("注册失败,请联系管理员!");
							uni.showToast({
								title: "注册失败,请联系管理员!",
								icon: "error"
							});
						}
					});
				}
			}
		}
	}
</script>

<style>
	.panel {
		position: relative;
		width: 60%;
		left: 20%;
		top: 20%;
	}

	.login-text {
		position: relative;
		top: 100px;
		left: 38%;
	}

	.account-input .account {
		position: relative;
		top: 190px;
		height: 40px;
		font-size: 20px;
		border-radius: 5px;
		background-color: white;
		border-bottom: black 2px solid;
		text-align: center;
	}

	.password-input .password {
		position: relative;
		top: 250px;
		border-radius: 5px;
		height: 40px;
		font-size: 20px;
		background-color: white;
		border-bottom: black 2px solid;
		text-align: center;
	}

	.phone-number-input .phone-number {
		position: relative;
		top: 310px;
		border-radius: 5px;
		height: 40px;
		font-size: 20px;
		background-color: white;
		border-bottom: black 2px solid;
		text-align: center;
	}

	.code-input .code {
		position: relative;
		top: 370px;
		border-radius: 5px;
		height: 40px;
		font-size: 20px;
		background-color: white;
		border-bottom: black 2px solid;
		text-align: center;
	}

	.code-btn .btn {
		position: relative;
		top: 380px;
		width: 60%;
		left: 15%;
		color: white;
		background-color: blue;
	}

	.register-btn .btn {
		position: relative;
		top: 440px;
		color: white;
		background-color: green;
	}

	image {
		z-index: -1;
		position: relative;
		width: 100%;
		height: 900px;
		top: -300px;
	}
</style>