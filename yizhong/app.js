//app.js
App({
  uploadimg: function (data){
    var that= this,
    i=data.i ? data.i : 0,
    success=data.success ? data.success : 0,
    fail=data.fail ? data.fail : 0;
    console.log('data数据'+data.path[i]);
    console.log('data数据1' + data.path[i][0]);
    console.log(i);
    wx.uploadFile({
      url: data.url,
     
      filePath: data.path[i],
      name: 'fileData',
      formData: {
        // id: data.id
      }, 
      
      success: (resp) => {
        success++;
        console.log(resp)
        console.log(i);
        },
      fail: (res) => {
        fail++;
        console.log('fail:' + i + "fail:" + fail);
      },
      complete: () => {
        console.log(i);
        i++;
        if (i == data.path.length) {   
          console.log('执行完毕');
          console.log('成功：' + success + " 失败：" + fail);
        } else { 
          console.log(i);
          data.i = i;
          data.success = success;
          data.fail = fail;
          that.uploadimg(data);
        }

      }
    });
  },


  onLaunch: function () {
    // 展示本地存储能力
    var logs = wx.getStorageSync('logs') || []
    logs.unshift(Date.now())
    wx.setStorageSync('logs', logs)

    // 登录
    wx.login({
      success: res => {
        // 发送 res.code 到后台换取 openId, sessionKey, unionId
      }
    })
    // 获取用户信息
    wx.getSetting({
      success: res => {
        if (res.authSetting['scope.userInfo']) {
          // 已经授权，可以直接调用 getUserInfo 获取头像昵称，不会弹框
          wx.getUserInfo({
            success: res => {
              // 可以将 res 发送给后台解码出 unionId
              this.globalData.userInfo = res.userInfo

              // 由于 getUserInfo 是网络请求，可能会在 Page.onLoad 之后才返回
              // 所以此处加入 callback 以防止这种情况
              if (this.userInfoReadyCallback) {
                this.userInfoReadyCallback(res)
              }
            }
          })
        }
      }
    })
  },
  globalData: {
    userInfo: null,
    submitImage: [
      '/images/sfzqm.jpg',
      '/images/sfzbm.jpg',
      '/images/certificate.jpg',
      '/images/zhengchesfz.jpg',
      '/images/zhengqianfang.jpg',
      '/images/zuoqianfang.jpg',
      '/images/youqianfang.jpg',
      '/images/youhoufang.jpg',
      '/images/zuohoufang.jpg',
      '/images/nameplate.jpg',
      '/images/engine.jpg',
       
    ],
    safeImage: [
      '/images/sfzqm.jpg',
      '/images/sfzbm.jpg',
      '/images/certificate.jpg',
      
      '/images/zhengchesfz.jpg',
      '/images/zhengqianfang.jpg',
      '/images/zuoqianfang.jpg',
      '/images/youqianfang.jpg',
      '/images/youhoufang.jpg',

      '/images/zuohoufang.jpg',
      '/images/nameplate.jpg',
      '/images/engine.jpg',
      
    ],
    operationImage: [
      '/images/sfzqm.jpg',
      '/images/sfzbm.jpg',
      '/images/rentou.jpg',
    ],

    registerImage:[
      '/images/sfzqm.jpg',
      '/images/sfzbm.jpg',
      '/images/sfzqm.jpg',
      '/images/sfzbm.jpg',
      '/images/certificate.jpg',

      '/images/invoice.jpg',
     '/images/purchaseContract.jpg',
      '/images/zhengchesfz.jpg',
      '/images/zhengqianfang.jpg',
      '/images/zuoqianfang.jpg',
      '/images/youqianfang.jpg',
      '/images/youhoufang.jpg',
      '/images/zuohoufang.jpg',

      '/images/nameplate.jpg',
      '/images/dajiazisfz.jpg',
      '/images/engine.jpg',
      '/images/yeyabeng.jpg',
    ]
    // sfzqm: submitImage[0],
    // sfzbm: submitImage[],
    // rentou: '/images/rentou.jpg',
    // hegezheng: '/images/hegezheng.jpg',
    // fapiao: '/images/fapiao.jpg',
    // caigouhetong: '/images/caigouhetong.jpg',
    // shouquanzhengshu: '/images/shouquanzhengshu.jpg',
    // zhengchesfz: '/images/zhengchesfz.jpg',
    // zhengqianfang: '/images/zhengqianfang.jpg',
    // zuoqianfang: '/images/zuoqianfang.jpg',
    // youqianfang: '/images/youqianfang.jpg',
    // youhoufang: '/images/youhoufang.jpg',
    // zuohoufang: '/images/zuohoufang.jpg',
    // mingpaisfz: '/images/mingpaisfz.jpg',
    // dajiazisfz: '/images/dajiazisfz.jpg',
    // fadongji: '/images/fadongji.jpg',
    // yeyabeng: '/images/yeyabeng.jpg',
  }
})