<template>
	<view>
		<view class="header">
			<h1 class="header-text">好友验证</h1>
		</view>
		<view class="friend-list-panel">
			<view class="request-add-friend-list" v-for="(item, index) in nick_name" :key="index" :style="style">
				<view v-show="friend_request_list[index].status == '等待验证'">
					<image :src="head_image[index]" mode="" class="new-friend-icon"></image>
					<p class="new-friend-text">{{nick_name[index]}}</p>
					<p class="new-friend-text2">{{request_content}}</p>
					<button class="refuse-add-friend-btn" @click="refuseAddNewFriend()">拒绝</button>
					<button class="agree-add-friend-btn" @click="agreeAddNewFriend()">同意</button>
				</view>
			</view>
		</view>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				nick_name: [],
				head_image: [],
				request_content: "请求加为好友!",
				request_people_id: [],
				personal_id: "",
				friend_request_list: [],
				primary_ID: 0,
				friend_request_table_ID: 0,
				my_friend_information_table_ID: 0,
				is_should_mod_status: false,
				style: {
					background_color: "white"
				},
				my_info:{
					head_image: "",
					nick_name: "",
					personal_id: ""
				},
				friend_info: {
					head_image: "",
					nick_name: "",
					personal_id: ""
				},
			}
		},
		onLoad() {
			let that = this;
			this.personal_id = variable.personal_id;
			uni.request({
				url: this.$baseUrl + "FriendRequest/findAll",
				method: "GET",
				success: (response) => {
					if(response.data.data.length != 0){
						for(var i = 0;i < response.data.data.length;i++){
							if((that.personal_id == response.data.data[i].receive_people_id || that.personal_id == response.data.data[i].request_people_id) && response.data.data[i].status == "等待验证"){
								that.request_people_id.push(response.data.data[i].request_people_id);
								that.friend_request_list.push(response.data.data[i]);
							}
						}
					}
				}
			});
			this.getRequestPeopleInformation();
		},
		methods: {
			modStatus(my_personal_id, friend_personal_id){
				let that = this;
				uni.request({
					url: this.$baseUrl + "FriendRequest/findAll",
					method: "GET",
					success: (response) => {
						if(response.data.data.length != 0){
							for(var i = 0;i < response.data.data.length;i++){
								if(my_personal_id == response.data.data[i].request_people_id && friend_personal_id == response.data.data[i].receive_people_id){
									that.friend_request_table_ID = response.data.data[i].id;
									that.is_should_mod_status = true;
									break;
								}else{
									if(i != response.data.data.length - 1){
										continue;
									}else{
										that.is_should_mod_status = false;
									}
								}
							}
						}
					}
				});
			},
			updateNewFriendInformationID(){
				let that = this;
				uni.request({
					url: "FriendRequest/getMyFriendInformationTableID",
					method: "GET",
					success: (response) => {
						if(response.data.data != "" && response.data.data != null){
							that.my_friend_information_table_ID = parseInt(response.data.data) + 1;
							that.$forceUpdate();
							// alert("my_friend_information_table_ID:" + that.my_friend_information_table_ID);
						}
					}
				});
			},
			getRequestPeopleInformation(){
				let that = this;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/findAll",
					method: "GET",
					success: (response) => {
						if(response.data.data.length != 0){
							for(var i = 0;i < response.data.data.length;i++){
								for(var j = 0;j < that.request_people_id.length;j++){
									if(that.request_people_id[j] == response.data.data[i].personal_id){
										that.style.background_color = "lightcyan";
										that.head_image.push(response.data.data[i].head_image);
										that.nick_name.push(response.data.data[i].nick_name);
										that.my_info.head_image = response.data.data[i].head_image;
										that.my_info.nick_name = response.data.data[i].nick_name;
										that.my_info.personal_id = response.data.data[i].personal_id;
									}
								}
								if(variable.id == response.data.data[i].id){
									that.friend_info.head_image = response.data.data[i].head_image;
									that.friend_info.nick_name = response.data.data[i].nick_name;
									that.friend_info.personal_id = response.data.data[i].personal_id;
								}
							}
						}else{
							that.head_image[0] = "../../static/head_image.png";
						}
						that.modStatus(that.my_info.personal_id, that.friend_info.personal_id);
					}
				});
			},
			agreeAddNewFriend(){
				let that = this;
				if(this.is_should_mod_status){
					uni.request({
						url: this.$baseUrl + "FriendRequest/modStatus?id=" + parseInt(this.friend_request_table_ID) + "&status=验证通过",
						method: "GET",
						success: (response) => {
							console.log(response);
							that.updateNewFriendInformationID();
							alert("好友添加成功!");
							uni.request({
								url: this.$baseUrl + "MyFriendInformation/addOrMod",
								method: "POST",
								data:{
									id: that.my_friend_information_table_ID,
									my_head_image: that.my_info.head_image,
									my_nick_name: that.my_info.nick_name,
									my_personal_id: that.my_info.personal_id,
									friend_head_image: that.friend_info.head_image,
									friend_nick_name: that.friend_info.nick_name,
									friend_personal_id: that.friend_info.personal_id
								},
								success: (res) => {
									console.log(res);
								}
							});
							uni.request({
								url: this.$baseUrl + "ChatContent/add",
								method: "POST",
								data: {
									my_head_image: that.friend_info.head_image,
									my_nick_name: that.friend_info.nick_name,
									my_personal_id: that.friend_info.personal_id,
									friend_head_image: that.my_info.head_image,
									friend_nick_name: that.my_info.nick_name,
									friend_personal_id: that.my_info.personal_id,
									content: "我通过你的好友请求,现在我们可以开始聊天了!!!"
								},
								success: (response) => {
									console.log(response);
								}
							});
						}
					});
				}
				uni.navigateTo({
					url: "/pages/friendList/friendList"
				});
				this.$forceUpdate();
			},
			refuseAddNewFriend(){
				uni.request({
					url: this.$baseUrl + "FriendRequest/modStatus?id=" + parseInt(this.friend_request_table_ID) + "&status=验证不通过",
					method: "GET",
					success: (response) => {
						console.log(response);
						alert("好友已拒绝添加!");
					}
				});
				uni.navigateTo({
					url: "/pages/friendList/friendList"
				});
				this.$forceUpdate();
			}
		}
	}
</script>

<style>
	.header-text{
		position: relative;
		left: 38%;
	}
	.request-add-friend-list{
		background-color: lightcyan;
		height: 90px;
	}
	.new-friend-icon{
		width: 50px;
		height: 50px;
		border-radius: 100px;
		position: relative;
		left: 10%;
		top: 20px;
	}
	.new-friend-text{
		font-size: 30px;
		position: relative;
		left: 26%;
		bottom: 45px;
	}
	.new-friend-text2{
		position: relative;
		left: 26%;
		bottom: 45px;
	}
	.refuse-add-friend-btn{
		position: relative;
		background-color: red;
		color: white;
		width: 20%;
		left: 12%;
		bottom: 90px;
	}
	.agree-add-friend-btn{
		position: relative;
		background-color: green;
		color: white;
		width: 20%;
		left: 35%;
		bottom: 136px;
	}
</style>
