<template>
	<view class="no-data">
		<image src="/static/image/nodata.png" />
		<view class="no-data-title">
			暂无数据
		</view>
	</view>
</template>

<script>
	export default {
		data() {
			return {

			}
		},
		methods: {

		}
	}
</script>

<style lang="scss">
	.no-data {
		display: inline-block;
		text-align: center;
		width: 100%;
		padding-top: 166upx;
		padding-bottom: 100upx;

		&-title {
			padding-top: 40upx;
			font-size: 30upx;
			font-family: PingFangSC-Medium, PingFang SC;
			color: #999;
			line-height: 42upx;
		}

		&-info {
			padding-top: 7upx;
			font-size: 24upx;
			font-family: PingFangSC-Regular, PingFang SC;
			font-weight: 400;
			color: rgba(0, 0, 0, 0.45);
			line-height: 33upx;
		}

		image {
			width: 469upx;
			height: 389upx;
		}
	}
</style>

<style>

</style>
