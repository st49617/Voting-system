webpackJsonp([1],{"7zck":function(t,e){},NHnr:function(t,e,n){"use strict";Object.defineProperty(e,"__esModule",{value:!0});var r=n("7+uW"),o={render:function(){var t=this.$createElement,e=this._self._c||t;return e("v-app",{attrs:{id:"app"}},[e("router-view")],1)},staticRenderFns:[]};var a,i=n("VU/8")({name:"App"},o,!1,function(t){n("TKwY")},null,null).exports,s=n("/ocq"),u=n("bOdI"),l=n.n(u),c=n("mtWM"),v=n.n(c).a.create((a={baseURL:"http://localhost:8080",timeout:1e3},l()(a,"timeout",3e4),l()(a,"withCredentials",!0),a));function p(){return $cookies.isKey("AUTH_TOKEN")?$cookies.get("AUTH_TOKEN"):""}var d=function(){return""!==p()&&null!==p()},m={name:"Menu",data:function(){return{}},methods:{pepa:function(){var t;!function(t){var e=p();v.post("api/meeting/add",t,{headers:{Authorization:"Bearer "+e}})}({start:"2019-10-10T14:08:56.235-0700"}),(t=p(),v.get("api/meeting/get-all",{headers:{Authorization:"Bearer "+t}})).then(function(t){console.log(t.data)})},userIsLogged:function(){return d()},goToLogin:function(){this.$router.push({name:"Login"})},goToVoting:function(){this.$router.push({name:"Voting"})},goToSuggestion:function(){this.$router.push({name:"Suggestion"})},goToMeeting:function(){this.$router.push({name:"Meeting"})},goToUserStatistic:function(){this.$router.push({name:"UserStatistic"})}}},g={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-center":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-layout",{attrs:{"align-space-around":"","justify-center":"",column:"","fill-height":"","px-10":""}},[n("v-btn",{attrs:{large:"",color:"primary"},on:{click:function(e){return t.goToLogin()}}},[t._v("Login")]),t._v(" "),t.userIsLogged()?n("v-btn",{attrs:{large:"",color:"primary"},on:{click:function(e){return t.goToVoting()}}},[t._v("Hlasování")]):t._e(),t._v(" "),t.userIsLogged()?n("v-btn",{attrs:{large:"",color:"primary"},on:{click:function(e){return t.goToUserStatistic()}}},[t._v("Statistiky")]):t._e(),t._v(" "),n("v-btn",{attrs:{large:"",color:"primary"},on:{click:function(e){return t.goToMeeting()}}},[t._v("Předchozí zastupitelstva")]),t._v(" "),n("v-btn",{attrs:{large:"",color:"primary"},on:{click:function(e){return t.goToSuggestion()}}},[t._v("Správa návrhů")])],1)],1)],1)],1)},staticRenderFns:[]},f=n("VU/8")(m,g,!1,null,null,null).exports,h={name:"Login",data:function(){return{credentials:{email:"",password:""},rules:{show:!0,required:function(t){return!!t||"Povinné pole."},min:function(t){return t.length>=8||"Minimální délka hesla je 8 znaků"}}}},methods:{login:function(){var t,e=this;(t=this.credentials,v.post("api/user/login",t)).then(function(t){$cookies.set("AUTH_TOKEN",t.data),e.$router.push({name:"Menu"})}).catch(function(t){console.log("Error login"),console.log(t)})},logout:function(){$cookies.set("AUTH_TOKEN",""),this.$router.push({name:"Menu"})},userIsLogged:function(){return d()},backToMenu:function(){this.$router.push({name:"Menu"})}}},_={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-space-around":"","fill-height":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-card",[n("v-card-title",{attrs:{"primary-title":""}},[t.userIsLogged()?t._e():n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Přihlášení")]),t._v(" "),n("v-text-field",{attrs:{rules:[t.rules.required],type:"text",name:"input-email",label:"E-mail",hint:"Zadejte E-mail"},model:{value:t.credentials.email,callback:function(e){t.$set(t.credentials,"email",e)},expression:"credentials.email"}}),t._v(" "),n("v-text-field",{attrs:{rules:[t.rules.required,t.rules.min],type:"password",name:"input-password",label:"Heslo",hint:"Zadejte heslo"},model:{value:t.credentials.password,callback:function(e){t.$set(t.credentials,"password",e)},expression:"credentials.password"}})],1),t._v(" "),t.userIsLogged()?n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Odhlášení")])]):t._e()],1),t._v(" "),n("v-card-actions",[n("v-layout",{attrs:{"justify-space-around":""}},[n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.backToMenu()}}},[t._v("Zpět")]),t._v(" "),t.userIsLogged()?t._e():n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.login()}}},[t._v("Přihlásit")]),t._v(" "),t.userIsLogged()?n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.logout()}}},[t._v("Odhlásit")]):t._e()],1)],1)],1)],1)],1)],1)},staticRenderFns:[]},y=n("VU/8")(h,_,!1,null,null,null).exports,k={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-space-around":"","fill-height":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-card",[n("v-card-title",{attrs:{"primary-title":""}},[n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Hlasování")])])],1),t._v(" "),n("v-card-actions",[n("v-layout",{attrs:{"justify-space-around":""}},[n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.backToMenu()}}},[t._v("Zpět")]),t._v(" "),n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.goToMeeting()}}},[t._v("Zastupitelstvo")])],1)],1)],1)],1)],1)],1)},staticRenderFns:[]},b=n("VU/8")({name:"Voting",data:function(){return{}},methods:{backToMenu:function(){this.$router.push({name:"Menu"})},goToMeeting:function(){this.$router.push({name:"Meeting"})}}},k,!1,null,null,null).exports,T={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-space-around":"","fill-height":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-card",[n("v-card-title",{attrs:{"primary-title":""}},[n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Zastupitelstvo")])])],1),t._v(" "),n("v-card-actions",[n("v-layout",{attrs:{"justify-space-around":""}},[n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.backToMenu()}}},[t._v("Zpět")]),t._v(" "),n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.goToSuggestion()}}},[t._v("Bod programu")])],1)],1)],1)],1)],1)],1)},staticRenderFns:[]},M=n("VU/8")({name:"Meeting",data:function(){return{}},methods:{backToMenu:function(){this.$router.push({name:"Menu"})},goToSuggestion:function(){this.$router.push({name:"Suggestion"})}}},T,!1,null,null,null).exports,$={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-space-around":"","fill-height":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-card",[n("v-card-title",{attrs:{"primary-title":""}},[n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Bod Programu")])])],1),t._v(" "),n("v-card-actions",[n("v-layout",{attrs:{"justify-space-around":""}},[n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.backToMenu()}}},[t._v("Zpět")]),t._v(" "),n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.goToMeeting()}}},[t._v("Zastupitelstvo")]),t._v(" "),n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.goToUserStatistic()}}},[t._v("Statistika zastupitele")])],1)],1)],1)],1)],1)],1)},staticRenderFns:[]},x=n("VU/8")({name:"Meeting",data:function(){return{}},methods:{backToMenu:function(){this.$router.push({name:"Menu"})},goToMeeting:function(){this.$router.push({name:"Meeting"})},goToUserStatistic:function(){this.$router.push({name:"UserStatistic"})}}},$,!1,null,null,null).exports,S={render:function(){var t=this,e=t.$createElement,n=t._self._c||e;return n("div",[n("v-layout",{attrs:{"justify-space-around":"","fill-height":""}},[n("v-flex",{attrs:{lg3:"",md4:"",sm12:"","px-5":""}},[n("v-card",[n("v-card-title",{attrs:{"primary-title":""}},[n("v-layout",{attrs:{column:""}},[n("h3",{staticClass:"headline mb-0"},[t._v("Statistika zastupitele")])])],1),t._v(" "),n("v-card-actions",[n("v-layout",{attrs:{"justify-space-around":""}},[n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.backToMenu()}}},[t._v("Zpět")]),t._v(" "),n("v-btn",{attrs:{color:"primary"},on:{click:function(e){return t.goToSuggestion()}}},[t._v("Bod Programu")])],1)],1)],1)],1)],1)],1)},staticRenderFns:[]},U=n("VU/8")({name:"Meeting",data:function(){return{}},methods:{backToMenu:function(){this.$router.push({name:"Menu"})},goToSuggestion:function(){this.$router.push({name:"Suggestion"})}}},S,!1,null,null,null).exports;r.default.use(s.a);var j=new s.a({routes:[{path:"/",name:"Menu",component:f},{path:"/login",name:"Login",component:y},{path:"/voting",name:"Voting",component:b},{path:"/meeting",name:"Meeting",component:M},{path:"/suggestion",name:"Suggestion",component:x},{path:"/user-statistic",name:"UserStatistic",component:U}]}),w=n("3EgV"),E=n.n(w),L=(n("7zck"),n("NYxO"));r.default.use(L.a);var V=new L.a.Store({state:{token:null},mutations:{LOGIN_SUCCESS:function(t,e){t.token=e}}});n("ppUw");r.default.use(E.a,{theme:{primary:"#8bc34a",secondary:"#2196f3",accent:"#ffc107",error:"#f44336"}}),r.default.config.productionTip=!1,new r.default({el:"#app",router:j,components:{App:i},template:"<App/>",store:V})},TKwY:function(t,e){}},["NHnr"]);
//# sourceMappingURL=app.js.map