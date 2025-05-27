<template>
	<view class="content">
		<h1 class="friend-list-header">好友列表</h1>
		<view class="friend-list-panel">
			<view class="request-add-friend-list" @click="goToVerifyFriendPage">
				<image src="../../static/new_friend_icon.png" mode="" class="new-friend-icon"></image>
				<p class="new-friend-text">新的朋友</p>
			</view>
			<view v-for="(item, index) in friend_list" :key="index" @click="goToChatPage(index)">
				<view v-show="friend_list[index].my_personal_id == personal_id" class="friend-list">
					<image :src="friend_list[index].friend_head_image" mode="" class="new-friend-icon2"></image>
					<p class="new-friend-text2">{{friend_list[index].friend_nick_name}}</p>
					<p class="text-content">{{content.slice(0, 16)}}……</p>
				</view>
				<view v-show="friend_list[index].friend_personal_id == personal_id" class="friend-list">
					<image :src="friend_list[index].my_head_image" mode="" class="new-friend-icon2"></image>
					<p class="new-friend-text2">{{friend_list[index].my_nick_name}}</p>
					<p class="text-content">{{content.slice(0, 16)}}……</p>
				</view>
			</view>
		</view>
		<view class="footer">
			<myFooter></myFooter>
		</view>
	</view>
</template>

<script>
	import myFooter from "../footer/footer.vue";
	import variable from "../../common/help.js";
	export default {
		components: {
			myFooter
		},
		data() {
			return {
				friend_list: [],
				personal_id: "",
				content: "我通过了你的好友请求,现在我们可以开始聊天了!"
			}
		},
		onLoad() {
			let that = this;
			this.personal_id = variable.personal_id;
			// uni.request({
			// 	url: this.$baseUrl + "ChatContent/findAll",
			// 	method: "GET",
			// 	success: (response) => {
			// 		if (response.data.data.length != 0) {
			// 			for (var i = 0; i < response.data.data.length; i++) {
			// 				var p1 = response.data.data[0].my_personal_id;
			// 				var p2 = response.data.data[0].friend_personal_id;
			// 				if(p1 == that.personal_id || p2 == that.personal_id){
								
			// 				}
			// 				if(i == response.data.data.length - 1){
			// 					that.content = response.data.data[i].content;
			// 				}
			// 			}
			// 		}
			// 	}
			// });
			this.getFriendList();
		},
		methods: {
			getFriendList() {
				let that = this;
				uni.request({
					url: this.$baseUrl + "MyFriendInformation/findAll",
					method: "GET",
					success: (response) => {
						that.friend_list = response.data.data;
						// for(var i = 0;i < response.data.data.length;i++){
						// 	if(response.data.data[i].my_personal_id == this.personal_id || response.data.data[i].friend_personal_id == this.personal_id){
						// 		that.style.background_color = "lightgray";
						// 	}
						// }
					}
				});
			},
			goToVerifyFriendPage() {
				uni.navigateTo({
					url: "/pages/verifyFriend/verifyFriend"
				});
			},
			goToChatPage(index) {
				uni.navigateTo({
					url: `/pages/chat/chat?friend_list=${encodeURIComponent(JSON.stringify(this.friend_list[index]))}`
				});
			}
		}
	}
</script>

<style>
	.friend-list-header {
		position: relative;
		left: 32%;
	}

	.footer {
		position: fixed;
		bottom: 0;
		width: 100%;
	}

	.request-add-friend-list {
		background-color: lightcyan;
		height: 90px;
	}

	.new-friend-icon {
		width: 50px;
		height: 50px;
		position: relative;
		left: 10%;
		top: 20px;
	}

	.friend-list {
		background-color: lightgray;
		height: 90px;
	}

	.new-friend-text {
		font-size: 30px;
		float: right;
		position: relative;
		right: 35%;
		top: 25px;
	}

	.new-friend-icon2 {
		width: 50px;
		height: 50px;
		border-radius: 100px;
		position: relative;
		left: 10%;
		top: 20px;
	}

	.new-friend-text2 {
		font-size: 30px;
		position: relative;
		left: 25%;
		bottom: 40px;
	}

	.text-content {
		position: relative;
		left: 25%;
		bottom: 40px;
	}
</style>