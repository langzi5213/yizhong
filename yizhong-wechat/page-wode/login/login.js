// page-wode/login/login.js
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  userNameInput: function (e) {
    this.setData({
      userName: e.detail.value
    })
  },
  userPasswordInput: function (e) {
    this.setData({
      userPassword: e.detail.value
    })
    console.log(e.detail.value)
  },
  logIn: function () {
    wx.navigateTo({
      url: '/page-wode/home/home'
    })
    var that = this
    wx.request({
      url: 'https://www.gcjxglzx.com', 
      data: {cur: 1 },
      method: 'GET', 
       success: function (json) {
        wx.showModal({
          title: '提示',
          content: JSON.stringify(json.data),
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
            }
          }
        })
  }
})
  }  ,

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad: function (options) {

      },

      /**
       * 生命周期函数--监听页面初次渲染完成
       */
      onReady: function () {

      },

      /**
       * 生命周期函数--监听页面显示
       */
      onShow: function () {

      },

      /**
       * 生命周期函数--监听页面隐藏
       */
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

      }
    })