 
const app = getApp()

Page({
  data: {
    motto: 'Hello World',
    userInfo: {},
    hasUserInfo: false,
    canIUse: wx.canIUse('button.open-type.getUserInfo'),
 
  },
   
  // bindViewTap: function () {
  //   wx.navigateTo({
  //     url: 'pages/logs/logs'
  //   })
  // },


  onLoad: function () {
    var code
    wx.login({
     success: function (res) {
        code = res.code 
        console.log("获取到的code："+code)
        // wx.request({
        //   url: 'https://api.weixin.qq.com/sns/jscode2session?appid=wx92bbe0a5e2b54fe0&secret=yzjx1234&js_code=' + code + '&grant_type=authorization_code',
          
        //   data: {},
        //   header: {
        //     'content-type': 'application/json'
        //   },
        //   success: function (res) {
        //     var openid = res.data.openid
        //     console.log("获取到的用户数据11：" + openid)
        //     console.log("获取到的用户数据：" + url)
        //   }
        // })
      }
    })
    

    if (app.globalData.userInfo) {
      this.setData({
        userInfo: app.globalData.userInfo,
        hasUserInfo: true
      })
    } else if (this.data.canIUse) {
       
      app.userInfoReadyCallback = res => {
        this.setData({
          userInfo: res.userInfo,
          hasUserInfo: true
        })
      }
    } else {
       
      wx.getUserInfo({
        success: res => {
          app.globalData.userInfo = res.userInfo
          this.setData({
            userInfo: res.userInfo,
            hasUserInfo: true
          })
        }
      })
    }
  },
  getUserInfo: function (e) {
    console.log("用户数据"+e)
    app.globalData.userInfo = e.detail.userInfo
    this.setData({
      userInfo: e.detail.userInfo,
      hasUserInfo: true
    })
  }
})
