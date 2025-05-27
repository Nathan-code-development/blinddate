<template>
	<view>
		<footer class="page-footer">
			<view class="recommend">
				<image src="../../static/recommend_icon.jpg" class="recommend-img" @click="goToRecommendPage()"></image>
				<p class="p-recommend">推荐</p>
			</view>
			<view class="chat">
				<image src="../../static/chat_icon.png" class="chat-img" @click="goToFriendPage()"></image>
				<p class="p-chat">聊天</p>
			</view>
			<view class="myself">
				<image src="../../static/myself_icon.png" class="myself-img" @click="goToMyselfPage()"></image>
				<p class="p-myself">我的</p>
			</view>
		</footer>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				name: ""
			}
		},
		onLoad() {
			this.getRealName();
		},
		methods: {
			getRealName(){
				let that = this;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/findAll",
					method: "GET",
					success: (response) => {
						for(var i = 0;i < response.data.data.length;i++){
							if(variable.id == response.data.data[i].id){
								that.name = response.data.data[i].name;
							}
						}
					}
				});
			},
			goToRecommendPage(){
				uni.navigateTo({
					url: "/pages/recommend/recommend"
				});
			},
			goToFriendPage(){
				uni.navigateTo({
					url: "/pages/friendList/friendList"
				});
			},
			goToMyselfPage(){
				uni.navigateTo({
					url: "/pages/myself/myself?name=" + this.name
				});
			}
		}
	}
</script>

<style>
	.page-footer{
		height: 55px;
		background-color: white;
	}
	.recommend-img{
		position: relative;
		width: 30px;
		height: 30px;
		left: 8%;
	}
	.p-recommend{
		position: relative;
		font-size: 15px;
		left: 8%;
	}
	.chat-img{
		position: relative;
		width: 30px;
		height: 30px;
		left: 46%;
		bottom: 53px;
	}
	.p-chat{
		position: relative;
		font-size: 15px;
		left: 46%;
		bottom: 55px;
	}
	.myself-img{
		position: relative;
		width: 30px;
		height: 30px;
		left: 86%;
		bottom: 106px;
	}
	.p-myself{
		position: relative;
		font-size: 15px;
		left: 86%;
		bottom: 108px;
	}
</style>
