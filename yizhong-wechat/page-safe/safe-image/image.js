// page-safe/safe-image/image.js
const app = getApp()
Page({
 
  data: {
    sfzqm: app.globalData.safeImage[0],
    sfzbm: app.globalData.safeImage[1],
    hegezheng: app.globalData.safeImage[2],
    zhengchesfz: app.globalData.safeImage[3],
    zhengqianfang: app.globalData.safeImage[4],
    zuoqianfang: app.globalData.safeImage[5],
    youqianfang: app.globalData.safeImage[6],
    youhoufang: app.globalData.safeImage[7],
    zuohoufang: app.globalData.safeImage[8],
    namePlate: app.globalData.safeImage[9],
    engineNameplate: app.globalData.safeImage[10],
  },
 

  submit: function (e) {
    app.uploadimg({
      url: 'https://www.gcjxglzx.com/insure/save',
      path: app.globalData.safeImage,
    })
    // wx.showToast({
    //   title: '提交成功',
    //   icon: 'success',
    //   duration: 2000
    // })  
     
    

     
  },
  onLoad: function (options) {
  
  },

  
  onReady: function () {
  
  },
 
  onShow: function () {
  
  },

  
  onHide: function () {
  
  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {
  
  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
  
  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {
  
  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
  
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
            zhengchesfz: res.tempFilePaths
          })
        } else if (e.target.id === '4') {
          self.setData({
            zhengqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '5') {
          self.setData({
            zuoqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '6') {
          self.setData({
            youqianfang: res.tempFilePaths
          })
        } else if (e.target.id === '7') {
          self.setData({
            youhoufang: res.tempFilePaths
          })
        } else if (e.target.id === '8') {
          self.setData({
            zuohoufang: res.tempFilePaths
          })
        } else if (e.target.id === '9') {
          self.setData({
            namePlate: res.tempFilePaths
          })
        } else if (e.target.id === '10') {
          self.setData({
            engineNameplate: res.tempFilePaths
          })
        }
      }
    });
  },
})