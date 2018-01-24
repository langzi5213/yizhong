const app = getApp()
Page({

  data: {
    date: {
      startDate: "2017-01-01",
      endDate: "2200-01-01",
      selectDate: "2018-01-01"
    },

    sfzqm: app.globalData.registerImage[0],
    sfzbm: app.globalData.registerImage[1],
    spouseSfzqm: app.globalData.registerImage[2],
    spouseSfzbm: app.globalData.registerImage[3],
    certificate: app.globalData.registerImage[4],

    invoice: app.globalData.registerImage[5],
    purchaseContract: app.globalData.registerImage[6],
    zhengchesfz: app.globalData.registerImage[7],
    zhengqianfang: app.globalData.registerImage[8],
    zuoqianfang: app.globalData.registerImage[9],

    youqianfang: app.globalData.registerImage[10],
    youhoufang: app.globalData.registerImage[11],
    zuohoufang: app.globalData.registerImage[12],
    namePlate: app.globalData.registerImage[13],
    dajiazisfz: app.globalData.registerImage[14],
    
    engine: app.globalData.registerImage[15],
    yeyabeng: app.globalData.registerImage[16],

    qylxr: false

  },

  registerChange: function (e) {
    console.log('选中了' + e.detail.value);
    if (e.detail.value === '2') {
      this.setData({
        qylxr: true
      })
    }
    if (e.detail.value === '1') {
      this.setData({
        qylxr: false
      })
    }
  },
  submit: function (e) {

    var ownerName = e.detail.value.ownerName
    var identityNum = e.detail.value.identityNum
    var buyDate = e.detail.value.buyDate
    var plateNum = e.detail.value.plateNum
    var bigShelfNum = e.detail.value.bigShelfNum
    var enginePlateNum = e.detail.value.enginePlateNum
    var hydraulicPlateNum = e.detail.value.hydraulicPlateNum
    var remark = e.detail.value.remark

    console.log(ownerName);
    console.log(identityNum);
    console.log(buyDate);
    console.log(plateNum);
    console.log(bigShelfNum);
    console.log(enginePlateNum);
    console.log(hydraulicPlateNum);
    console.log(remark);

    wx.request({
      url: 'http://www.gcjxglzx.com/machinery/save',
      data: {
        ownerName: ownerName,
        identityNum: identityNum,
        buyDate: buyDate,
        plateNum: plateNum,
        bigShelfNum: bigShelfNum,
        enginePlateNum: enginePlateNum,
        hydraulicPlateNum: hydraulicPlateNum,
        remark: remark,
      },
      header: {
        'content-type': 'application/json' // 默认值
      },
      success: function (res) {
        console.log(res.data)
      }
    })

  },
  homeRegister: function () {
    wx.navigateTo({
      url: '/page-register/register1/reg-page1'
    })
  },
  dateChange: function (e) {
    this.setData({
      selectDate: e.detail.value
    })
  },

  chooseImage: function (e) {
    console.log(e.target.id)
    var self = this;
    wx.chooseImage({
      count: 1,
      sizeType: ['original', 'compressed'],
      sourceType: ['album', 'camera'],
      success: function (res) {
        console.log(res.tempFilePaths);
        app.globalData.submitImage[e.target.id] = res.tempFilePaths
        if (e.target.id === '0') {
          self.setData({
            sfzqm: res.tempFilePaths,
          })
        } else if (e.target.id === '1') {
          self.setData({
            sfzbm: res.tempFilePaths
          })
        } else if (e.target.id === '2') {
          self.setData({
            hegezheng: res.tempFilePaths
          })
        } else if (e.target.id === '3') {
          self.setData({
            fapiao: res.tempFilePaths
          })
        } else if (e.target.id === '4') {
          self.setData({
            caigouhetong: res.tempFilePaths
          })
        } else if (e.target.id === '5') {
          self.setData({
            shouquanzhengshu: res.tempFilePaths
          })
        } else if (e.target.id === '6') {
          self.setData({
            zhengchesfz: res.tempFilePaths
          })
        } else if (e.target.id === '7') {
          self.setData({
            zhengqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '8') {
          self.setData({
            zuoqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '9') {
          self.setData({
            youqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '10') {
          self.setData({
            youhoufang: res.tempFilePaths
          })
        } else if (e.target.id === '11') {
          self.setData({
            zuohoufang: res.tempFilePaths
          })
        } else if (e.target.id === '12') {
          self.setData({
            mingpaisfz: res.tempFilePaths
          })
        } else if (e.target.id === '13') {
          self.setData({
            dajiazisfz: res.tempFilePaths
          })
        } else if (e.target.id === '14') {
          self.setData({
            fadongji: res.tempFilePaths
          })
        } else if (e.target.id === '15') {
          self.setData({
            yeyabeng: res.tempFilePaths
          })
        }
      }
    });
  },



  onLoad: function (options) {
    var ownerName = wx.getStorageSync('ownerName');
    var identityNum = wx.getStorageSync('identityNum');
    var buyDate = wx.getStorageSync('buyDate');
    var plateNum = wx.getStorageSync('plateNum');
    var bigShelfNum = wx.getStorageSync('bigShelfNum');
    var enginePlateNum = wx.getStorageSync('enginePlateNum');
    var hydraulicPlateNum = wx.getStorageSync('hydraulicPlateNum');
    var remark = wx.getStorageSync('remark');
    this.setData({
      ownerName: ownerName,
      identityNum: identityNum,
      buyDate: buyDate,
      plateNum: plateNum,
      bigShelfNum: bigShelfNum,
      enginePlateNum: enginePlateNum,
      hydraulicPlateNum: hydraulicPlateNum,
      remark: remark,
    });
  },

  onReady: function () {

  },

  onShow: function () {

  },


  onHide: function () {

  },

  onUnload: function () {

  },

  onPullDownRefresh: function () {

  },


  onReachBottom: function () {

  },

  onShareAppMessage: function () {

  }
})