<template>
	<view class="content">
		<view class="panel">
			<h1 class="about-our">关于我们</h1>
			<hr />
			<p>这个APP由程序员独立开发,开发这个APP的目的是可以让更多的人可以利用手机APP来谈恋爱或相亲,从而减少了线下找人的时间,可以先从网上面认识对方。如有任何疑问,可以随时联系开发人员,开发人员的联系方式在APP最下方!</p>
			<hr />
			<p>学会一下数字暗语,能够有效帮助提升双方的好感度,是提高情商的利器!!!</p>
			<hr />
			<h2>下面是在恋爱关系中常用数字暗语:</h2>
			<hr />
			<h1 class="love-number-mean">爱情数字密码:</h1>
			<h2>0字开头:</h2>
			<p> 0451 你是唯一
				0452 你是我爱
				01925 你依旧爱我
				02825 你爱不爱我
				04527 你是我爱妻
				04551 你是我唯一
				0457 你是我妻
				0487 你是白痴
				0564335 你无聊时想想我
				06537 你惹我生气
				0748 你去死吧
				08056 你不理我了
				0837 你别生气
				095 你找我
				098 你走吧</p>
			<h2>1字开头:</h2>
			<p> 1314 一生一世
				13920 一生就爱你
				1414 要死要死
				1457 你是我妻
				147 一世情
				1451 你是唯一
				1573 一往情深
				1920 依旧爱你
				1930 依旧想你
				18340一巴扇死你</p>
			<h2>2字开头:</h2>
			<p> 20 爱你
				20110 爱你一百一十年
				20184 爱你一辈子
				2030999 爱你想你久久久
				2013614 爱你一生又一世
				2037 为你伤心
				259758 爱我就娶我吧
				245437/246437 爱是如此神奇
				2627 爱来爱去
				2612爱了又爱
				282 饿不饿
				2010000爱你一万年
				291314爱就一生一世
				257534爱我亲我三次
				240爱死你</p>
			<h2>3字开头:</h2>
			<p> 30 想你
				3344 生生世世
				306 想你了（喽）
				30920 想你就爱你
				3013 想你一生
				359258 想我就爱我吧
				360 想念你
				329958 神啊救救我吧
				3731 真心真意
				39 Thank you
				30920 想你就爱你
				3710 想起了你
				3257 深爱我妻</p>
			<h2>4字开头:</h2>
			<p> 440295 谢谢你爱过我
				447735 时时刻刻想我
				4456 速速回来
				456 是我啦
				460 思念你
				4980 只有为你
				48 是吧
				442230 时时刻刻想你
				446 世世有</p>
			<h2>5字开头:</h2>
			<p> 510190 我一定要追你
				520184 
				54033 我是你先生
				5406 我是你的
				5407 我是你妻
				54064 我是你老师
				54079 我是你知己
				5976 我到家了
				52067 我爱你不变
				5871 我不介意
				520/521 我爱你（五月20-21日是
				5120 我也爱你
				5148  我一世发
				59420 我就是爱你</p>
			<h2>6字开头:</h2>
			<p> 609 到永久
				6120 懒得理你
				6785753 老地方不见不散
				6868 溜吧溜吧
				687 对不起
				6699 顺顺利利</p>
			<h2>7字开头:</h2>
			<p> 70345 请你相信我
				768 起来吧
				70626 请你留下来
				7087 请你别走
				70885 请你帮帮我
				721 亲爱你
				729 去喝酒
				70345 请你相信我
				780 牵挂你
				706519184 请你让我依靠一辈子
				7708801314520 亲亲你抱抱你一生一世我爱你
				7708801314680 亲亲你抱抱你一生一世陪伴你
				75942305201314 猜我就是爱上你 我爱你一生一世
				7758521亲亲我吧我爱你</p>
			<h2>8字开头:</h2>
			<p> 8006 不理你了
				8013 伴你一生
				8023 我爱你（手势LOVE）
				8074 把你气死
				88 Bye Bye
				8834760 漫漫相思只为你
				898 分手吧</p>
			<h2>9字开头:</h2>
			<p> 902535/702535 求你爱我想我
				9089/7089 求你别走
				95 救我
				9520 就我爱你
				98 早安
				987 对不起
				9908875 求求你别抛弃我
				9421 就是爱你
				9494 就是就是
				9694482664 我喜欢你（不是谐音，是九键）
				995 救救我</p>
		</view>
		<view class="return-page">
			<button class="return-page-btn" @click="returnPage">返回</button>
		</view>
		<view class="version">
			<p class="p-text">版权:1.0.0 若想联系开发者,可发送信息到开发者的邮件,邮件为nls1175235190@qq.com。开发者的电话号码为:13388421389</p>
		</view>
	</view>
</template>

<script>
	import variable from "../../common/help.js";
	export default {
		onLoad() {
			this.getRealName();
		},
		data() {
			return {
				name: ""
			}
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
			returnPage(){
				uni.navigateTo({
					url: "/pages/myself/myself?name=" + this.name
				});
			}
		}
	}
</script>

<style>
	.panel{
		position: relative;
		background-color: pink;
		top: 50px;
		width: 90%;
		left: 5%;
		border-radius: 10px;
	}
	.about-our{
		position: relative;
		left: 35%;
	}
	p{
		position: relative;
		font-size: 23px;
	}
	.return-page .return-page-btn{
		position: relative;
		top: 80px;
		width: 90%;
		background-color: aqua;
	}
	.p-text{
		font-size: 15px;
	}
	.version .p-text{
		width: 90%;
		position: relative;
		top: 100px;
		left: 5%;
	}
</style>
