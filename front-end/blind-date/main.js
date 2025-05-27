import App from './App'

// #ifndef VUE3
import Vue from 'vue'
import './uni.promisify.adaptor'
import myRequest from './common/request.js';
Vue.config.productionTip = false
Vue.prototype.$baseUrl = "http://121.40.144.4:54920/";
Vue.prototype.$account = "";
Vue.prototype.$personal_id = "";
Vue.prototype.$head_image = "";
Vue.prototype.$myRequest = myRequest;
App.mpType = 'app'
const app = new Vue({
  ...App
})
app.$mount()
// #endif

// #ifdef VUE3
import { createSSRApp } from 'vue'
export function createApp() {
  const app = createSSRApp(App)
  return {
    app
  }
}
// #endif