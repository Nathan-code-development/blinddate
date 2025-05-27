const BASE_URL="http://localhost:54920/"; //开发时使用本地接口，在上线时只需要修改此处接口为线上地址即可
 
export const myRequest=(options)=>{ //传入的options是一个json对象
	return new Promise((resolve,reject)=>{
		uni.request({
			url:BASE_URL+options.url,
			method:options.methods||"GET",
			data:options.data || {},
			dataType:options.dataType || "json",
			success: (res) => {
				if(res.data.status !== 0){
					return uni.showToast({
						title:"获取数据失败"
					})
				}
				resolve(res)
			},
			fail: (err) => {
				uni.showToast({
					title:"接口请求失败"
				})
				reject(err)
			}
		})
	})
}