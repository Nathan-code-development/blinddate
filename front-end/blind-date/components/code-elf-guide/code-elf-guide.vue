<template>
	<view class="content">
		<swiper class="swiper" 
		:autoplay="autoplay"
		indicator-dots="true"
		indicator-color="rgba(255,255,255,.4)"
		indicator-active-color="rgba(255,255,255,.8)"
		@change="guideChange"
		:duration="duration">
			<swiper-item v-for="(item,index) in imglist" :key-="index">
				<view class="swiper-item">
					<view class="swiper-item-img"><image :src="item.imgUrl" mode="scaleToFill"></image></view>
				</view>
			</swiper-item>
		</swiper>
		<view v-show="guideBtn" class="jump-over">
			<view>IP端口设置</view>
		</view>
		<view v-show="guideBtn" class="experience" @tap="launchFlag()">立即体验</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {
				background: ['color1', 'color2', 'color3'],
				autoplay: false,
				duration: 500,
				guideBtn: false
			}
		},
		props: {
			imglist: [Array]
		},
		methods: {
			launchFlag: function(){
				/**
				 * 向本地存储中设置launchFlag的值，即启动标识；
				 */
				// uni.setStorage({
				// 		key: 'launchFlag',
				// 		data: true,
				// });
				uni.switchTab({
					url: '/pages/index/index'
				});
				
			},
			guideChange(e) {
				this.guideBtn = e.detail.current==4 ? true : false;
			}
		}
	}
</script>
<style>
	page,
	.content{
		width: 100%;
		height: 100%;
		padding: 0;
	}
	.swiper{
		width: 100%;
		height: 100%;
		background: #000;
	}
	.swiper-item {
		width: 100%;
		height: 100%;
		text-align: center;
		position: relative;
		display: flex;
		/* justify-content: center; */
		align-items:flex-end;
		flex-direction:column-reverse
	}
	.swiper-item-img{
		width: 100%;
	}
	.swiper-item-img image{
		width: 100%;
		height: 100vh;
	}
	
	.jump-over,.experience{
		position: absolute;
		height: 60upx;
		line-height: 60upx;
		padding: 0 40upx;
		font-size: 32upx;
		color: #007AFF;
		z-index: 99;
	}
	.jump-over{
		right: 30upx;
		top: 80upx;
	}
	.experience{
		color: #fff;
		border: 1px solid #fff;
		border-radius: 30upx;
		right: 50%;
		margin-right: -105upx;
		bottom: 50px;
	}
</style>
