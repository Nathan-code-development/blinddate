<template>
	<view class="content">
		<h1>推荐对象</h1>
		<view class="all-and-recommend-label">
			<p class="all-label" @click="selectAll()" :style="style1">全部</p>
			<p class="recommend-label" @click="selectRecommend()" :style="style2">推荐</p>
		</view>
		<view v-if="style1.color == 'red' && style2.color == 'black'">
			<view class="card" v-for="(item,index) in user_info_list" :key="index">
				<image :src="user_info_list[index].head_image" class="img"></image>
				<p class="nick-name">昵称:{{user_info_list[index].nick_name}}</p>
				<p class="age">年龄:{{user_info_list[index].age}}</p>
				<p class="simple-introduction">自我介绍:{{user_info_list[index].simple_introduction.slice(0, 10)}}……</p>
				<button class="detail-btn" @click="goToDetailsPage(index)">详情</button>
			</view>
		</view>
		<view v-else-if="style2.color == 'red' && style1.color == 'black'">
			<view class="card" v-for="(item,index) in recommend_info_list" :key="index">
				<image :src="recommend_info_list[index].head_image" class="img"></image>
				<p class="nick-name">昵称:{{recommend_info_list[index].nick_name}}</p>
				<p class="age">年龄:{{recommend_info_list[index].age}}</p>
				<p class="simple-introduction">自我介绍:{{recommend_info_list[index].simple_introduction.slice(0, 10)}}……</p>
				<button class="detail-btn" @click="goToDetailsPage(index)">详情</button>
			</view>
		</view>
		<view class="paginition">
			<uni-pagination :total="total" :show-icon="true" :pageSize="pageSize" :current="pageNum" @change="pageChanged"></uni-pagination>
		</view>
		<view class="footer">
			<myFooter></myFooter>
		</view>
	</view>
</template>

<script>
	import myFooter from "../footer/footer.vue";
	import variable from "../../common/help.js";
	import uniPaginition from "@/components/uni-pagination/uni-pagination.vue";
	export default {
		components: {
			myFooter
		},
		data() {
			return {
				style1: {
					color: "red"
				},
				style2: {
					color: "black"
				},
				total: 0,
				len1: 0,
				len2: 0,
				length_male: 0,
				length_female: 0,
				nick_name: "",
				src: "",
				gender: "",
				user_info_list: [],
				recommend_info_list: [],
				real_user_info_list: [],
				real_recommend_info_list: [],
				age: 18,
				lowest_age_range: 0,
				highest_age_range: 0,
				age_range: "",
				isRightAge: false,
				household_registration: "",
				hobby: "",
				education_background: "",
				occupation: "",
				salary_yearly: "",
				car_and_house_status: "",
				pageNum: 1,
				pageSize: 4,
			}
		},
		onLoad() {
			let that = this;
			this.nick_name = variable.nick_name;
			this.age_range = variable.age;
			this.household_registration = variable.expect_household_registration;
			this.hobby = variable.hobby;
			this.education_background = variable.education_background;
			this.occupation = variable.occupation;
			this.salary_yearly = variable.salary_yearly;
			this.car_and_house_status = variable.car_and_house_status;
			uni.request({
				url: this.$baseUrl + "PersonalInformation/findAllPro?gender=" + variable.gender,
				method: "GET",
				success: (response) => {
					that.len1 = response.data.data.length;
					that.total = that.len1;
					that.real_user_info_list = response.data.data;
				}
			});
			uni.request({
				url: this.$baseUrl + "PersonalInformation/findOrderData?gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
				method: "GET",
				success: (response) => {
					console.log(response);
					if(response.data.data.length != 0){
						for (var i = 0; i < response.data.data.length; i++) {
							if (that.nick_name != response.data.data[i].nick_name) {
								that.user_info_list.push(response.data.data[i]);
							}
						}
					}
				}
			});
			// "16-24", "25-30", "31-35", "36-40", "41-45", "46-50"
			switch(this.age_range){
				case "16-24": this.lowest_age_range = 16; this.highest_age_range = 24;break;
				case "25-30": this.lowest_age_range = 25; this.highest_age_range = 30;break;
				case "31-35": this.lowest_age_range = 31; this.highest_age_range = 35;break;
				case "36-40": this.lowest_age_range = 36; this.highest_age_range = 40;break;
				case "41-45": this.lowest_age_range = 41; this.highest_age_range = 45;break;
				case "46-50": this.lowest_age_range = 46; this.highest_age_range = 50;break;
			}
			uni.request({
				url: this.$baseUrl + "PersonalInformation/recommendPeople?household_registration=" + this.household_registration + "&gender=" + variable.gender,
				method: "GET",
				success: (response) => {
					console.log(response);
					that.len2 = 0;
					that.real_recommend_info_list.length = 0;
					if(response.data.data.length != 0){
						for(var i = 0;i < response.data.data.length;i++){
							if(that.lowest_age_range <= response.data.data[i].age && response.data.data[i].age <= that.highest_age_range){
								that.len2++;
								that.real_recommend_info_list.push(response.data.data[i]);
							}
						}
					}
				}
			});
			uni.request({
				url: this.$baseUrl + "PersonalInformation/recommendPeoplePro?household_registration=" + this.household_registration + "&gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
				method: "GET",
				success: (response) => {
					console.log(response);
					if(response.data.data.length != 0){
						for(var i = 0;i < response.data.data.length;i++){
							if(that.lowest_age_range <= response.data.data[i].age && response.data.data[i].age <= that.highest_age_range){
								that.recommend_info_list.push(response.data.data[i]);
							}
						}
					}
				}
			});
		},
		methods: {
			selectAll(){
				let that = this;
				this.style1.color = "red";
				this.style2.color = "black";
				this.total = this.len1;
				this.pageNum = 1;
				this.recommend_info_list.length = 0;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/recommendPeoplePro?household_registration=" + this.household_registration + "&gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
					method: "GET",
					success: (response) => {
						console.log(response);
						if(response.data.data.length != 0){
							for(var i = 0;i < response.data.data.length;i++){
								if(that.lowest_age_range <= response.data.data[i].age && response.data.data[i].age <= that.highest_age_range){
									that.recommend_info_list.push(response.data.data[i]);
								}
							}
						}
					}
				});
				this.$forceUpdate();
			},
			selectRecommend(){
				let that = this;
				this.style1.color = "black";
				this.style2.color = "red";
				this.total = this.len2;
				this.pageNum = 1;
				this.user_info_list.length = 0;
				uni.request({
					url: this.$baseUrl + "PersonalInformation/findOrderData?gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
					method: "GET",
					success: (response) => {
						console.log(response);
						if(response.data.data.length != 0){
							for (var i = 0; i < response.data.data.length; i++) {
								if (that.nick_name != response.data.data[i].nick_name) {
									that.user_info_list.push(response.data.data[i]);
								}
							}
						}
					}
				});
				this.$forceUpdate();
			},
			pageChanged(e) {
				let that = this;
				this.pageNum = e.current;
				console.log(e.current);
				if(this.style1.color == "red"){
					// this.real_user_info_list.length = 0;
					// 清空数据并重新添加
					this.user_info_list.length = 0;
					this.recommend_info_list.length = 0;
					uni.request({
						url: this.$baseUrl + "PersonalInformation/findOrderData?gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
						method: "GET",
						success: (response) => {
							console.log(response);
							if(response.data.data.length != 0){
								for (var i = 0; i < response.data.data.length; i++) {
									if (that.nick_name != response.data.data[i].nick_name) {
										that.user_info_list.push(response.data.data[i]);
									}
								}
							}
						}
					});
					// 当推荐标签点击触发时,推荐的所有数据(推荐的数据,是通过期望对象的年龄范围和地区来分配的),将数据回到首页并展示4条数据
					uni.request({
						url: this.$baseUrl + "PersonalInformation/recommendPeoplePro?household_registration=" + this.household_registration + "&gender=" + variable.gender + "&pageNum=" + 1 + "&pageSize=" + this.pageSize,
						method: "GET",
						success: (response) => {
							console.log(response);
							if(response.data.data.length != 0){
								for(var i = 0;i < response.data.data.length;i++){
									// 判断年龄是否是在自己期望的对象年龄范围之内。
									if(that.lowest_age_range <= response.data.data[i].age && response.data.data[i].age <= that.highest_age_range){
										that.recommend_info_list.push(response.data.data[i]);
									}
								}
							}
						}
					});
				}else if(this.style2.color == "red"){
					// 清空数据并重新添加
					this.recommend_info_list.length = 0;
					this.user_info_list.length = 0;
					uni.request({
						url: this.$baseUrl + "PersonalInformation/recommendPeoplePro?household_registration=" + this.household_registration + "&gender=" + variable.gender + "&pageNum=" + this.pageNum + "&pageSize=" + this.pageSize,
						method: "GET",
						success: (response) => {
							console.log(response);
							if(response.data.data.length != 0){
								for(var i = 0;i < response.data.data.length;i++){
									// that.recommend_info_list.length = response.data.data.length;
									if(that.lowest_age_range <= response.data.data[i].age && response.data.data[i].age <= that.highest_age_range){
										that.recommend_info_list.push(response.data.data[i]);
									}
								}
							}
						}
					});
					// 当全部标签点击触发时,全部的所有数据,将数据回到首页并展示4条数据
					uni.request({
						url: this.$baseUrl + "PersonalInformation/findOrderData?gender=" + variable.gender + "&pageNum=" + 1 + "&pageSize=" + this.pageSize,
						method: "GET",
						success: (response) => {
							console.log(response);
							if(response.data.data.length != 0){
								for (var i = 0; i < response.data.data.length; i++) {
									if (that.nick_name != response.data.data[i].nick_name) {
										that.user_info_list.push(response.data.data[i]);
									}
								}
							}
						}
					});
				}
			},
			goToDetailsPage(index){
				if(this.style1.color == 'red'){
					uni.navigateTo({
						url: "/pages/detailsAndAddFriend/detailsAndAddFriend?nick_name=" + this.real_user_info_list[index + (this.pageNum - 1) * this.pageSize].nick_name
					});
				}else if(this.style2.color == 'red'){
					uni.navigateTo({
						url: "/pages/detailsAndAddFriend/detailsAndAddFriend?nick_name=" + this.real_recommend_info_list[index + (this.pageNum - 1) * this.pageSize].nick_name
					});
				}
			}
		}
	}
</script>

<style>
	h1{
		position: relative;
		left: 37%;
	}
	.footer{
		position: fixed;
		bottom: 0;
		width: 100%;
	}
	.all-and-recommend-label{
		position: relative;
		width: 100%;
		height: 60px;
	}
	.all-label{
		position: relative;
		font-size: 40px;
		left: 10%;
		text-decoration: underline;
	}
	.recommend-label{
		position: relative;
		font-size: 40px;
		left: 70%;
		bottom: 50px;
		text-decoration: underline;
	}
	.card{
		float: left;
		width: 40%;
		position: relative;
		left: 3%;
		margin-top: 5%;
		margin-left: 5%;
		height: 320px;
		background-color: hotpink;
		border-radius: 10px;
		color: white;
	}
	.img{
		width: 60px;
		height: 60px;
		position: relative;
		left: 33%;
		top: 20px;
		border-radius: 100px;
	}
	.nick-name{
		position: relative;
		top: 30px;
		left: 15%;
		width: 70%;
		text-align: center;
	}
	.age{
		position: relative;
		top: 50px;
		left: 15%;
		width: 70%;
		text-align: center;
	}
	.simple-introduction{
		position: relative;
		top: 70px;
		left: 15%;
		width: 70%;
		text-align: center;
	}
	.detail-btn{
		position: relative;
		top: 80px;
		width: 70%;
		text-align: center;
		background-color: aqua;
		/* font-size: 15px; */
	}
	.paginition{
		position: fixed;
		bottom: 65px;
		width: 100%;
	}
</style>
