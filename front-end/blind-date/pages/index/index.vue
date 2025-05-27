<template>
	<view class="content">
		<view class="panel">
			<view class="login-text">
				<h1>登录</h1>
			</view>
			<view class="account-input">
				<input type="text" v-model="account" placeholder="请输入用户名:" class="account" />
			</view>
			<view class="password-input">
				<input type="safe-password" v-model="password" :password="true" placeholder="请输入密码:" class="password" />
			</view>
			<view class="login-btn">
				<button class="btn" @tap="login()">登录</button>
			</view>
			<view class="register-btn">
				<button class="btn" @tap="register()">注册</button>
			</view>
		</view>
		<image src="../../static/index_picture.png"></image>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		name: "login",
		data() {
			return {
				title: 'Hello',
				account: "",
				password: "",
				isAccount: false,
				isCorrectPassword: false
			}
		},
		onLoad() {

		},
		methods: {
			login: function() {
				uni.request({
					url: this.$baseUrl + "login/findAll",
					method: "GET",
					success: (res) => {
						if (res.data.data.length == 0) {
							this.ID = 1;
							variable.id = this.ID;
							// alert(variable.id);
						} else {
							for (var i = 0; i < res.data.data.length; i++) {
								if (this.account == res.data.data[i].account) {
									this.ID = i + 1;
									variable.id = this.ID;
									// alert(variable.id);
								}
							}
						}
					}
				});
				uni.request({
					url: this.$baseUrl + "login/findAll",
					method: "GET",
					success: (response) => {
						if (response.data.data.length == 0) {
							uni.showToast({
								title: "用户名不存在!",
								icon: "error"
							});
						} else {
							for (var i = 0; i < response.data.data.length; i++) {
								if (this.account == response.data.data[i].account) {
									this.isAccount = true;
								} else {
									if (i != response.data.data.length - 1) {
										continue;
									} else {
										this.isAccount = false;
									}
								}
								if (this.isAccount) {
									if (this.password == response.data.data[i].password) {
										variable.account = this.account;
										// alert("登录成功!");
										uni.showToast({
											title: "登录成功!",
											icon: "success"
										});
										uni.navigateTo({
											url: "/pages/myself/myself"
										});
										break;
									} else {
										// alert("密码不正确!");
										uni.showToast({
											title: "密码不正确!",
											icon: "error"
										});
										continue;
									}
								} else {
									// alert("用户名不存在!");
									uni.showToast({
										title: "用户名不存在!",
										icon: "error"
									});
								}
							}
						}
					}
				});
			},
			register: function() {
				uni.navigateTo({
					url: "/pages/register/register"
				});
			}
		}
	}
</script>

<style>
	.content {
		z-index: -1;
	}

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

	.login-btn .btn {
		position: relative;
		top: 310px;
		color: white;
		background-color: green;
		z-index: 1;
	}

	.register-btn .btn {
		position: relative;
		top: 350px;
		color: white;
		background-color: blue;
		z-index: 1;
	}

	image {
		z-index: -1;
		position: relative;
		width: 100%;
		height: 900px;
		top: -220px;
	}
</style>