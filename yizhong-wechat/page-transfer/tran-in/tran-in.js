// page-transfer/transfer1/tran-in.js
Page({

   
  data: {
    ownerInName: '请输入设备转入人姓名',
    ownerInId: '请输入转入人身份证号',
    enterpriseInName: '请输入企业联系人姓名',
    enterpriseInNum: '请输入企业代码',
    spouseInName: '请输入转入人配偶姓名',
    spouseInId: '请输入配偶身份证号码',
    ownerInPhone: '请输入转入人联系电话',
    tranInAddress: '请输入转入人有效通讯地址',

    qylxr: false,
  },

  nextMessage: function () {
    wx.navigateTo({
      url: '/page-register/reg-message/reg-message'
    })
  },
  tranInChange: function (e) {
    wx.setStorageSync('transfer-in-radio', e.detail.value);
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
  ownerInName: function (e) {
    wx.setStorageSync('ownerInName', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        ownerInName: "请输入设备转入人姓名",
      });
    }
    else {
      this.setData({
        ownerInName: e.detail.value,
      });
    }
  },
  ownerInId: function (e) {
    wx.setStorageSync('ownerInId', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        ownerInId: "请输入转入人身份证号",
      });
    }
    else {
      this.setData({
        ownerInId: e.detail.value,
      });
    }
  },
  enterpriseInName: function (e) {
    wx.setStorageSync('enterpriseInName', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        enterpriseInName: "请输入企业联系人姓名",
      });
    }
    else {
      this.setData({
        enterpriseInName: e.detail.value,
      });
    }
  },
  enterpriseInNum: function (e) {
    wx.setStorageSync('enterpriseInNum', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        enterpriseInNum: "请输入企业代码",
      });
    }
    else {
      this.setData({
        enterpriseInNum: e.detail.value,
      });
    }
  },
  spouseInName: function (e) {
    wx.setStorageSync('spouseInName', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        spouseInName: "请输入转入人配偶姓名",
      });
    }
    else {
      this.setData({
        spouseInName: e.detail.value,
      });
    }
  },
  spouseInId: function (e) {
    wx.setStorageSync('spouseInId', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        spouseInId: "请输入配偶身份证号码",
      });
    }
    else {
      this.setData({
        spouseInId: e.detail.value,
      });
    }
  },
  ownerInPhone: function (e) {
    wx.setStorageSync('ownerInPhone', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        ownerInPhone: "请输入转入人联系电话",
      });
    }
    else {
      this.setData({
        ownerInPhone: e.detail.value,
      });
    }
  },
  tranInAddress: function (e) {
    wx.setStorageSync('tranInAddress', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        tranInAddress: "请输入转入人有效通讯地址",
      });
    }
    else {
      this.setData({
        tranInAddress: e.detail.value,
      });
    }
  },


  onLoad: function (options) {
    var ownerInName = wx.getStorageSync('ownerInName');
    if (ownerInName === null||ownerInName === '') {
      ownerInName = "请输入设备转入人姓名";
    };
    var ownerInId = wx.getStorageSync('ownerInId');
    if (ownerInId === ''||ownerInId === ' ') {
      ownerInId = "请输入转入人身份证号";
    };
    var enterpriseInName = wx.getStorageSync('enterpriseInName');
    if (enterpriseInName === '' || enterpriseInName === ' ') {
      enterpriseInName = "请输入企业联系人姓名";
    };
    var enterpriseInNum = wx.getStorageSync('enterpriseInNum');
    if (enterpriseInNum === ''||enterpriseInNum === ' ') {
      enterpriseInNum = "请输入企业代码";
    };

    var spouseInName = wx.getStorageSync('spouseInName');
    if (spouseInName === ''||spouseInName === ' ') {
      spouseInName = "请输入转入人配偶姓名";
    };
    var spouseInId = wx.getStorageSync('spouseInId');
    if (spouseInId === ''||spouseInId === ' ') {
      spouseInId = "请输入配偶身份证号码";
    };
    var ownerInPhone = wx.getStorageSync('ownerInPhone');
    if (ownerInPhone === '' || ownerInPhone === ' ') {
      ownerInPhone = "请输入转入人联系电话";
    };
    var tranInAddress = wx.getStorageSync('tranInAddress');
    if (tranInAddress === ''||tranInAddress === ' ') {
      tranInAddress = "请输入转入人有效通讯地址";
    };

    this.setData({
      ownerInName: ownerInName,
      ownerInId: ownerInId,
      enterpriseInName: enterpriseInName,
      enterpriseInNum: enterpriseInNum,
      spouseInName: spouseInName,
      spouseInId: spouseInId,
      ownerInPhone: ownerInPhone,
      tranInAddress: tranInAddress,

    });
  },
 
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