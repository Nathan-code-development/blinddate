<template>
	<view class="content">
		<scroll-view class="chat-panel" :scroll-top="scrollTop" scroll-y="true" @scrolltoupper="upper"
			@scrolltolower="lower" @scroll="scroll" v-if="nick_name == my_info.nick_name">
			<view v-for="(item,index) in content" :key="index" v-show="valid[index]">
				<view v-if="!isMeToFriend[index]">
					<img :src="friend_info.head_image" alt="" class="my-head-image" :style="friend_head_image_style" />
					<view class="text-content" :style="text_content_style1">
						<p class="text2">{{item}}</p>
					</view>
				</view>
				<view v-else>
					<img :src="my_info.head_image" alt="" class="friend-head-image" :style="my_head_image_style" />
					<view class="text-content" :style="text_content_style2">
						<p class="text1">{{item}}</p>
					</view>
				</view>
			</view>
		</scroll-view>
		<scroll-view class="chat-panel" :scroll-top="scrollTop" scroll-y="true" @scrolltoupper="upper"
			@scrolltolower="lower" @scroll="scroll" v-else-if="nick_name == friend_info.nick_name">
			<view v-for="(item,index) in content" :key="index" v-show="valid[index]">
				<view v-if="isMeToFriend[index]">
					<img :src="friend_info.head_image" alt="" class="friend-head-image" :style="my_head_image_style" />
					<view class="text-content" :style="text_content_style2">
						<p class="text1">{{item}}</p>
					</view>
				</view>
				<view v-else>
					<img :src="my_info.head_image" alt="" class="my-head-image" :style="friend_head_image_style" />
					<view class="text-content" :style="text_content_style1">
						<p class="text2">{{item}}</p>
					</view>
				</view>
			</view>
		</scroll-view>
		<view class="chat-content-input">
			<textarea class="text-input" v-model="send_content"></textarea>
			<button class="send-btn" @click="sendMessage()">发送</button>
		</view>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		data() {
			return {
				offset: [],
				index: 0,
				timer: null,
				my_info: {
					head_image: "",
					nick_name: "",
					personal_id: ""
				},
				friend_info: {
					head_image: "",
					nick_name: "",
					personal_id: ""
				},
				friend_head_image_style: {
					width: "60px",
					height: "60px",
					position: "relative",
					border_radius: "100px"
				},
				my_head_image_style: {
					width: "60px",
					height: "60px",
					position: "relative",
					left: "86%",
					border_radius: "100px"
				},
				text_content_style1: {
					position: "relative",
					left: "20%",
					bottom: "40px",
					background: "lightblue"
				},
				text_content_style2: {
					position: "relative",
					left: "12%",
					bottom: "40px",
					background: "lightgreen"
				},
				isMeToFriend: [],
				friend_list: [],
				valid: [], // 判断聊天记录里面有没有我和我朋友的信息,如果发送方和接收方两者都有,那就说明我在和我某一个朋友聊天,或者说我某个朋友在和我聊天,在页面展示出来聊天内容。
				valid1: [], // 判断聊天记录里面有没有我和我朋友的信息,如果发送方和接收方两者都有,那就说明我在和我某一个朋友聊天,或者说我某个朋友在和我聊天,在页面展示出来聊天内容。
				valid2: [], // 判断聊天记录里面有没有我和我朋友的信息,如果发送方和接收方两者都有,那就说明我在和我某一个朋友聊天,或者说我某个朋友在和我聊天,在页面展示出来聊天内容。
				pid1: [],
				pid2: [],
				content1: [],
				content2: [],
				content: [],
				message: {
					my_msg: "",
					friend_msg: ""
				},
				personal_id: "",
				judge_personal_id: "",
				top: 80,
				send_content: "",
				nick_name: "",
				scrollTop: 0,
				old: {
					scrollTop: 0
				}
			}
		},
		onLoad(option) {
			let that = this;
			this.nick_name = variable.nick_name;
			this.personal_id = variable.personal_id;
			this.friend_list = JSON.parse(decodeURIComponent(option.friend_list));
			this.my_info.head_image = this.friend_list.my_head_image;
			this.my_info.nick_name = this.friend_list.my_nick_name;
			this.my_info.personal_id = this.friend_list.my_personal_id;
			this.friend_info.head_image = this.friend_list.friend_head_image;
			this.friend_info.nick_name = this.friend_list.friend_nick_name;
			this.friend_info.personal_id = this.friend_list.friend_personal_id;
			uni.request({
				url: this.$baseUrl + "ChatContent/findAll",
				method: "GET",
				success: (response) => {
					if (response.data.data.length != 0) {
						for (var i = 0; i < response.data.data.length; i++) {
							that.pid1.push(response.data.data[i].my_personal_id);
							that.pid2.push(response.data.data[i].friend_personal_id);
						}
						for (var i = 0; i < response.data.data.length; i++) {
							var p1 = that.pid1[i];
							var p2 = that.pid2[i];
							if ((response.data.data[i].my_personal_id == that.my_info.personal_id &&
									response.data.data[i].friend_personal_id == that.friend_info.personal_id
								) ||
								(response.data.data[i].my_personal_id == that.friend_info.personal_id &&
									response.data.data[i].friend_personal_id == that.my_info.personal_id)) {
								if ((p1 == that.my_info.personal_id || p1 == that.friend_info.personal_id) &&
									(p2 == that.my_info.personal_id || p2 == that.friend_info.personal_id)) {
									that.valid.push(true);
								} else {
									that.valid.push(false);
								}
							} else {
								that.valid.push(false);
							}
						}
					}
				}
			});
			this.timer = setInterval(that.getMessage(), 100);
			// this.getMessage();
		},
		onUnload() {
			clearInterval(this.timer);
			this.timer = null;
		},
		methods: {
			upper: function(e) {
				console.log(e)
			},
			lower: function(e) {
				console.log(e)
			},
			scroll: function(e) {
				console.log(e)
				this.old.scrollTop = e.detail.scrollTop
			},
			goTop: function(e) {
				// 解决view层不同步的问题
				this.scrollTop = this.old.scrollTop
				this.$nextTick(function() {
					this.scrollTop = 0
				});
				uni.showToast({
					icon: "none",
					title: "纵向滚动 scrollTop 值已被修改为 0"
				});
			},
			getMessage() {
				let that = this;
				this.content.length = 0;
				this.isMeToFriend.length = 0;
				// if (this.content.length == 0 && this.nick_name == this.friend_info.nick_name) {
				// 	this.isMeToFriend.push(false);
				// 	// this.content1.push("我通过你的好友请求,现在我们可以开始聊天了!!!");
				// 	// this.message.friend_msg = "我通过你的好友请求,现在我们可以开始聊天了!!!";
				// 	// this.message.my_msg = "";
				// 	// this.content.push(this.message);
				// 	this.content.push("我通过你的好友请求,现在我们可以开始聊天了!!!");
				// 	alert("1:true");
				// }
				// if (this.content.length == 0 && this.nick_name == this.my_info.nick_name) {
				// 	this.isMeToFriend.push(true);
				// 	// this.content2.push("我通过你的好友请求,现在我们可以开始聊天了!!!");
				// 	// this.message.my_msg = "我通过你的好友请求,现在我们可以开始聊天了!!!";
				// 	// this.message.friend_msg = "";
				// 	// this.content.push(this.message);
				// 	this.content.push("我通过你的好友请求,现在我们可以开始聊天了!!!");
				// 	alert("2:true");
				// }
				uni.request({
					url: this.$baseUrl + "ChatContent/findAll",
					method: "GET",
					success: (response) => {
						if (response.data.data.length != 0) {
							for (var i = 0; i < response.data.data.length; i++) {
								that.content.push(response.data.data[i].content);
								if (response.data.data[i].my_nick_name == that.nick_name) {
									that.isMeToFriend.push(true);
								} else {
									that.isMeToFriend.push(false);
								}
								// if (that.nick_name == response.data.data[i].my_nick_name) {
								// that.content1.push(response.data.data[i].content);
								// that.message.friend_msg = response.data.data[i].content;
								// that.message.my_msg = "";
								// that.content.push(that.message);
								// that.content.push(response.data.data[i].content);
								// } else if (that.nick_name == response.data.data[i].friend_nick_name) {
								// that.content2.push(response.data.data[i].content);
								// that.message.my_msg = response.data.data[i].content;
								// that.message.friend_msg = "";
								// that.content.push(that.message);
								// that.content.push(response.data.data[i].content);
								// }
							}
						}
					}
				});
			},
			addMessage() {
				if (this.nick_name == this.my_info.nick_name) {
					this.isMeToFriend.push(true);
					this.valid.push(true);
					// this.content1.push(this.send_content);
					// this.message.friend_msg = this.send_content;
					// this.message.my_msg = "";
					// this.content.push(this.message);
					this.content.push(this.send_content);
				} else if (this.nick_name == this.friend_info.nick_name) {
					this.isMeToFriend.push(false);
					this.valid.push(true);
					// this.content2.push(this.send_content);
					// this.message.my_msg = this.send_content;
					// this.message.friend_msg = "";
					// this.content.push(this.message);
					this.content.push(this.send_content);
				}
			},
			sendMessage() {
				let that = this;
				if (this.send_content != " " && this.send_content != null) {
					if (this.nick_name == this.my_info.nick_name) {
						uni.request({
							url: this.$baseUrl + "ChatContent/add",
							method: "POST",
							data: {
								my_head_image: this.my_info.head_image,
								my_nick_name: this.my_info.nick_name,
								my_personal_id: this.my_info.personal_id,
								friend_head_image: this.friend_info.head_image,
								friend_nick_name: this.friend_info.nick_name,
								friend_personal_id: this.friend_info.personal_id,
								content: this.send_content
							},
							success: (response) => {
								console.log(response);
								that.send_content = "";
								that.addMessage();
								that.getMessage();
								that.$forceUpdate();
							}
						});
					} else {
						uni.request({
							url: this.$baseUrl + "ChatContent/add",
							method: "POST",
							data: {
								my_head_image: this.friend_info.head_image,
								my_nick_name: this.friend_info.nick_name,
								my_personal_id: this.friend_info.personal_id,
								friend_head_image: this.my_info.head_image,
								friend_nick_name: this.my_info.nick_name,
								friend_personal_id: this.my_info.personal_id,
								content: this.send_content
							},
							success: (response) => {
								console.log(response);
								that.send_content = "";
								that.addMessage();
								that.getMessage();
								that.$forceUpdate();
							}
						});
					}
				} else {
					alert("不能发送空消息!");
					uni.showToast({
						title: "不能发送空消息!",
						icon: "error"
					});
				}
			}
		}
	}
</script>

<style>
	.content {
		background-color: pink;
		border-bottom: 8px pink solid;
		z-index: -1;
	}

	.header-text {
		position: relative;
		text-align: center;
	}

	.chat-panel {
		position: relative;
		height: 792px;
	}

	.text1 {
		text-align: right;
	}

	.text2 {
		text-align: left;
	}

	.text-content {
		max-width: 70%;
	}

	.chat-content-input {
		position: relative;
		height: 87px;
	}

	.text-input {
		position: relative;
		width: 70%;
		height: 45px;
		top: 20px;
		font-size: 35px;
		background-color: white;
	}

	.send-btn {
		width: 27%;
		bottom: 25px;
		left: 35%;
	}
</style>