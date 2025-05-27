<template>
	<view id="id" class="content">
		<scroll-view scroll-y="true" style="height: 100%;" v-show="!crControl" 
		@touchstart="refreshStart" @touchmove="refreshMove" @touchend="refreshEnd" @scrolltolower="loadMore">
			<refresh ref="refresh" @isRefresh='isRefresh'></refresh>
			<view v-for="(item, key) in reviewMsg" :key="key">
				<item :reviewMsg="item" @childReview="childReview"></item>
			</view>
			<!-- <loadmore :status="more"></loadmore> -->
		</scroll-view>
		<view class="childReview" v-show="crControl" :animation="animationData">
			<view class="cr-title">
				<text class="textSendMsg">评论详情</text>
				<uniicon type="closeempty" size="30" color="#757575" @click="closeCr"></uniicon>
			</view>
			<scroll-view scroll-y="true" style="height: calc(100% - 50px);">
				<view v-for="(titem, key) in childData" :key="key">
					<item :reviewMsg="titem"></item>
				</view>
			</scroll-view>
		</view>
	</view>
</template>

<script>
	import dnIcon from '../dn-icon/dn-icon.vue';
	import uniicon from '../../components/uni-icons/uni-icons.vue'
	import item from './item.vue';
	import refresh from '../refresh/refresh.vue';
	import loadmore from '../uni-load-more/uni-load-more.vue';
	export default {
		name: 'review',
		props: {
			reviewMsg: [Array],
			childData: [Array]
		},
		components: {
			uniicon,
			dnIcon,
			item,
			refresh,
			loadmore
		},
		data() {
			return {
				// 弹出列表详情动画
				animationData: '',
				// 弹出列表详情开关
				crControl: false,
				// 获取节点高度
				setHeight: '',
				// 上拉加载
				more: 'more',				
			}
		},
		onLoad() {

		},
		mounted() {
			this.animation = uni.createAnimation();
			const query = uni.createSelectorQuery().in(this);
			query.select('#id').boundingClientRect(data => {
				console.log("得到布局位置信息" + JSON.stringify(data));
				this.setHeight = data.height;
			}).exec();
		},
		methods: {
			childReview(item, key) {
				this.crControl = true;
				this.animation.translateY(this.setHeight).step();
				this.animationData = this.animation.export();
				this.$nextTick(function(){
					
					this.animation.translateY(0).step({ duration: 150 });
					this.animationData = this.animation.export();
				})
				this.$emit('childReview', item);
			},
			closeCr() {
				this.crControl = false;
			},
			// 刷新touch监听
			refreshStart(e) {
				this.$refs.refresh.refreshStart(e);
			},
			refreshMove(e) {
				this.$refs.refresh.refreshMove(e);
			},
			refreshEnd(e) {
				this.$refs.refresh.refreshEnd(e);
			},
			isRefresh() {
				let that = this;
				setTimeout(() => {
					uni.showToast({
						icon: 'none',
						title: '刷新成功'
					})
					this.$refs.refresh.endAfter() //刷新结束
				}, 1000)
			},
			loadMore() {
				this.more = "loading";
				setTimeout(() => {
					this.more = "noMore";
				}, 1000);
			}
		}
	}
</script>

<style>
	page {
		width: 100%;
		height: 100%;
	}
	
	.content {
		width: 100%;
		height: 100%;
		background-color: #fff;
	}
	
	.cenHost-Content {
		position: relative;
		width: 100%;
	}
	
	.cr-title {
		width: 100%;
		height: 50px;
		display: flex;
		flex-direction: row;
		justify-content: space-between;
		align-items: center;
		padding: 0 15px 0 15px;
		border-bottom: 1px solid #e8e8e8;
		box-sizing: border-box;
		background-color: #FFFFFF;
	}
	
	.childReview {
		position: absolute;
		margin: auto;
		width: 100%;
		height: 100%;
		background-color: #F9F9F9;
		display: flex;
		flex-direction: column;
	}
	
	.textSendMsg {
		font-size: 14px;
	}
</style>
