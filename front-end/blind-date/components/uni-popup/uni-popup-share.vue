<template>
	<view class="uni-popup-share">
		<view class="uni-share-content">
			<view v-if="bottomData.length>0" class="uni-share-content-box">
				<view class="uni-share-content-item" v-for="(item,index) in bottomData" :key="index" @click.stop="select(item)">
					<view class="itemIcon">
						<image class="uni-share-image" :src="item.icon" mode="widthFix"></image>
					</view>
					<text class="uni-share-text">{{item.text}}</text>
				</view>

			</view>
			<view class="resNull" v-else>未找到要查询的服务</view>
		</view>
		<view class="uni-share-button-box">
			<button class="uni-share-button" @click="close">取消</button>
		</view>
	</view>
</template>

<script>
	export default {
		name: 'UniPopupShare',
		props: {
			bottomData: {
				type: Array
			}
		},
		inject: ['popup'],
		data() {
			return {
			}
		},
		created() {},
		methods: {
			/**
			 * 选择内容
			 */
			select(item) {
				console.log(item);
				uni.navigateTo({
					url:item.url
				});
				this.popup.close()
			},
			/**
			 * 关闭窗口
			 */
			close() {
				this.popup.close()
			}
		}
	}
</script>
<style lang="scss" scoped>
	.uni-popup-share {
		background-color: #fff;
	}
	.uni-share-title {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		align-items: center;
		justify-content: center;
		height: 40px;
	}
	.uni-share-title-text {
		font-size: 14px;
		color: #666;
	}
	.uni-share-content {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		justify-content: center;
		padding-top: 10px;
	}
	
	.uni-share-content-box {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		flex-wrap: wrap;
		width: 360px;
	}
	
	.uni-share-content-item {
		width: 90px;
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: column;
		justify-content: center;
		padding: 10px 0;
		align-items: center;
	}
	
	.uni-share-content-item:active {
		background-color: #f5f5f5;
	}
	.itemIcon{
		width: 40px; height: 40px;
		/* padding: 5px 0; */
		background-color: #78a4fa;
		border-radius: 50%;
		margin: 0 auto;
		display: flex;
		align-items: center;
	}
	.itemIcon image{
		width: 50%;
		margin: 0 auto;
	}
	.uni-share-image {
		width: 30px;
		height: 30px;
	}
	
	.uni-share-text {
		margin-top: 10px;
		font-size: 14px;
		color: #3B4144;
	}
	
	.uni-share-button-box {
		/* #ifndef APP-NVUE */
		display: flex;
		/* #endif */
		flex-direction: row;
		padding: 10px 15px;
	}
	
	.uni-share-button {
		flex: 1;
		border-radius: 50px;
		color: #666;
		font-size: 16px;
	}
	
	.uni-share-button::after {
		border-radius: 50px;
	}
	.resNull{
		padding: 15px 0;
	}
</style>
