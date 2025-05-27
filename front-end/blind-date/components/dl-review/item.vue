<template>
	<view class="content">
		<view class="cenHost">
			<view class="cenHeadImgContent">
				<image class="headImg" :src="reviewMsg.headImgSrc"></image>
			</view>
			<view class="cenHostMsgContent">
				<view class="viewMb viewMb-space-between">
					<view>
						<text class="textSize">{{reviewMsg.userName}}</text>
						<!-- <text class="textCenMsg cenHostMsg2">LV{{reviewMsg.userLevel}}</text> -->
					</view>
					<!-- <view>
						<text class="followText">+ 关注</text>
					</view> -->
				</view>
				<view class="viewMb">
					<!-- <text class="cenHostMsg3 textCenMsg">#{{reviewMsg.cenId}}</text> -->
					<text class="cenHostMsg4 textCenMsg">{{reviewMsg.sendTime}}</text>
				</view>
				<view class="cenHostReview viewMb">
					<text class="textSendMsg">{{reviewMsg.sendMsg}}</text>
				</view>
				<!-- <view class="iconRow">
					<view>
						<dnIcon type="haoping" color="#C0C0C0"></dnIcon>
						<text style="padding-left: 5px;">{{reviewMsg.likeNum}}</text>
					</view>
					<view>
						<dnIcon type="zan" color="#C0C0C0"></dnIcon>
						<text style="padding-left: 5px;">{{reviewMsg.dislikeNum}}</text>
					</view>
					<view>
						<uniicon type="redo" size="20" color="#C0C0C0"></uniicon>
					</view>
				</view> -->
				<!-- <view class="threeReviewContent" @click="openChildReview(reviewMsg)" v-if="reviewMsg.reviewLess.length">
					<view class="threeReviewVueText" v-for="(reKey, key) in reviewMsg.reviewLess" :key="key">
						{{reKey.userName}}
						<text class="defaultBlack">回复</text>
						{{reKey.targetUserName}}
						<text class="defaultBlack">:{{reKey.sendMsg}}</text>
					</view>
					<view class="reviewNumContent" v-if="reviewMsg.reviewNum > 3">
						<text>共{{reviewMsg.reviewNum}}条回复</text>
						<dnIcon type="tiaozhuan" size="12" style="margin-left: 5px;"></dnIcon>
					</view>
				</view> -->
			</view>
		</view>
	</view>
</template>

<script>
	import dnIcon from '../dn-icon/dn-icon.vue';
	import uniicon from '../../components/uni-icons/uni-icons.vue'
	export default {
		name: 'review',
		props: {
			reviewMsg: [Object],
			childData: [Array]
		},
		components: {
			uniicon,
			dnIcon
		},
		data() {
			return {
				crControl: false
			}
		},
		onLoad() {

		},
		methods: {
			openChildReview(item) {
				this.crControl = true;
				this.$emit('childReview', item);
			},
			closeCr() {
				this.crControl = false;
			}
		}
	}
</script>

<style>
	.content {
		width: 100%;
		height: auto;
	}
	
	.cenHost-Content {
		position: relative;
		width: 100%;
	}
	
	.cr-title {
		width: 100%;
		height: 30px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		padding: 0 10px 0 10px;
		box-sizing: border-box;
		background-color: #FFFFFF;
	}
	
	.childReview {
		position: absolute;
		margin: auto;
		width: 100%;
		height: 100%;
		top: 0px;
		background-color: #F9F9F9;
		display: flex;
		flex-direction: column;
	}

	.headImg {
		width: 30px;
		height: 30px;
		border-radius: 30px;
	}

	.textSendMsg {
		font-size: 14px;
	}

	.textSize {
		font-size: 13px;
		color: #808080;
		margin-right: 10px;
	}

	.textCenMsg {
		color: #999999;
		font-size: 12px;
	}

	.defaultBlack {
		color: #000000;
	}

	.viewMb-space-between {
		display: flex;
		justify-content: space-between;
	}

	.viewMb {
		margin-bottom: 5px;
	}

	.followText {
		font-size: 14px;
		color: #fa7298;
		margin-right: 8px;
	}

	.content {
		width: 100%;
		height: 100%;
		display: flex;
		flex-direction: column;
		align-items: center;
		background-color: #F9F9F9;
	}

	.cenHost {
		position: relative;
		width: 100%;
		display: flex;
		flex-direction: row;
		justify-content: flex-start;
		border-bottom: 1px solid #F2F2F2;
		margin-top: 5px;
	}

	.cenHostMsgContent {
		width: calc(100% - 30px);
		margin: 5px 10px 10px 10px;
	}

	.cenHeadImgContent {
		height: 100%;
		margin: 10px;
	}

	.cenHostMsg1 {}

	.cenHostMsg2 {
		background-color: #ff6d00;
		color: #FFFFFF;
		font-size: 10px;
	}

	.cenHostMsg3 {
		color: #999999;
		margin-right: 10px;
	}

	.cenHostMsg4 {
		color: #999999;
	}

	.iconRow {
		width: 50%;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		margin-top: 10px;
		color: #999999;
		font-size: 12px;
	}

	.threeReviewContent {
		background-color: #f1f1f1;
		margin-top: 10px;
		margin-bottom: 10px;
		padding: 8px;
	}

	.threeReviewVueText {
		font-size: 14px;
		color: #5090cd;
		margin: 8px;
	}

	.reviewNumContent {
		color: #5090cd;
		font-size: 12px;
		margin-left: 8px;
	}
</style>
