
const app = getApp()

Page({
  data: {
    date: {
      safeBuyDate0: "2018-01-01",
      safeBuyDate: "2018-01-01",
      
    },

    applicantName0: '请输入投保人姓名',
    applicantId0: '请输入投保人身份证号',
    beneficiaryName0: '第一受益人姓名',
    insurantName0: '请输入被保险人姓名',
    insurantPhone0: '被保险人联系方式',
    invoiceTitle0: '请输入发票抬头',
    deviceBrand0: '请输入设备品牌',
    address0: '请输入保单邮寄地址',
    remark0: '请输入您的备注信息',


    applicantName: '请输入投保人姓名',
    applicantId: '请输入投保人身份证号',
    beneficiaryName: '第一受益人姓名',
    insurantName: '请输入被保险人姓名',
    insurantPhone: '被保险人联系方式',
    invoiceTitle: '请输入发票抬头',
    deviceBrand: '请输入设备品牌',
    address: '请输入保单邮寄地址',
    remark: '请输入您的备注信息',

  },







  applicantName: function (e) {
    wx.setStorageSync('applicantName', e.detail.value);
    this.setData({
      applicantName: e.detail.value,
    });
  },
  applicantId: function (e) {
    wx.setStorageSync('applicantId', e.detail.value);
    this.setData({
      applicantId: e.detail.value,
    });
  },
  beneficiaryName: function (e) {
    wx.setStorageSync('beneficiaryName', e.detail.value);
    this.setData({
      beneficiaryName: e.detail.value,
    });
  },
  insurantName: function (e) {
    wx.setStorageSync('insurantName', e.detail.value);
    this.setData({
      insurantName: e.detail.value,
    });
  },
  insurantPhone: function (e) {
    wx.setStorageSync('insurantPhone', e.detail.value);
    this.setData({
      insurantPhone: e.detail.value,
    });
  },

 
  invoiceTitle: function (e) {
    wx.setStorageSync('invoiceTitle', e.detail.value);
    this.setData({
      invoiceTitle: e.detail.value,
    });
  },
  buyDateChange:function(e){
    wx.setStorageSync('safeBuyDate', e.detail.value);
    this.setData({
      safeBuyDate: e.detail.value,
    });
  },

  
  deviceBrand: function (e) {
    wx.setStorageSync('deviceBrand', e.detail.value);
    this.setData({
      deviceBrand: e.detail.value,
    });
  },

  // namePlate: function (e) {
  //   wx.setStorageSync('namePlate', e.detail.value);
  //   this.setData({
  //     namePlate: e.detail.value,
  //   });
  // },
  
  // engineNameplate: function (e) {
  //   wx.setStorageSync('engineNameplate', e.detail.value);
  //   this.setData({
  //     engineNameplate: e.detail.value,
  //   });
  // },
  address: function (e) {
    wx.setStorageSync('address', e.detail.value);
    this.setData({
      address: e.detail.value,
    });
  },
  remark: function (e) {
    wx.setStorageSync('remark', e.detail.value);
    this.setData({
      remark: e.detail.value,
    });
  },
   
  
  
  submit: function (e) {
    var applicantName = wx.getStorageSync('applicantName');
    var applicantId = wx.getStorageSync('applicantId');
    var beneficiaryName = wx.getStorageSync('beneficiaryName');
    var insurantName = wx.getStorageSync('insurantName');
    var insurantPhone = wx.getStorageSync('insurantPhone');
    var invoiceTitle = wx.getStorageSync('invoiceTitle');
    var safeBuyDate = wx.getStorageSync('safeBuyDate');
    var deviceBrand = wx.getStorageSync('deviceBrand');
    var address = wx.getStorageSync('address');
    var remark = wx.getStorageSync('remark');

 
  if(applicantName === '') {
      wx.showToast({
        title: '请输入投保人姓名',
        icon: 'loading'
      })
    } else if (applicantId === '') {
      wx.showToast({
        title: '请输入投保人身份证号',
        icon: 'loading'
      })
    } else if (beneficiaryName === '') {
      wx.showToast({
        title: '第一受益人姓名',
        icon: 'loading'
      })
  } else if (insurantName === '') {
    wx.showToast({
      title: '请输入被保险人姓名',
      icon: 'loading'
    })
  } else if (insurantPhone === '') {
    wx.showToast({
      title: '被保险人联系方式',
      icon: 'loading'
    })
  } else if (invoiceTitle === '') {
    wx.showToast({
      title: '请输入发票抬头',
      icon: 'loading'
    })
  } else if (deviceBrand === '') {
    wx.showToast({
      title: '请输入设备品牌',
      icon: 'loading'
    })
  } else if (address === '') {
    wx.showToast({
      title: '请输入保单邮寄地址',
      icon: 'loading'
    })
  }else{

    wx.navigateTo({
      url: '/page-safe/safe-message/message'
    })}
  },

reset:function(e){
  this.setData({
    applicantName: this.data.applicantName0,
    applicantId: this.data.applicantId0,
    insurantName: this.data.insurantName0,
    insurantPhone: this.data.insurantPhone0,
    beneficiaryName: this.data.beneficiaryName0,
    invoiceTitle: this.data.invoiceTitle0,
    safeBuyDate: '',
    deviceBrand: this.data.deviceBrand0,
    address: this.data.address0,
    remark: this.data.remark0,
  })
  wx.setStorageSync('applicantName', '')
  wx.setStorageSync('applicantId', '')
  wx.setStorageSync('insurantName', '')
  wx.setStorageSync('insurantPhone', '')
  wx.setStorageSync('beneficiaryName', '')
  wx.setStorageSync('invoiceTitle', '')
  wx.setStorageSync('safeBuyDate', '')
  wx.setStorageSync('deviceBrand', '')
  wx.setStorageSync('address', '')
  wx.setStorageSync('remark', '')
},
 
  



  onLoad: function (options) {
   

    var applicantName = wx.getStorageSync('applicantName');
    if (applicantName === '') {
      applicantName = this.data.applicantName0;
    };
    var applicantId = wx.getStorageSync('applicantId');
    if (applicantId === '') {
      applicantId = this.data.applicantId0;
    };
    var insurantName = wx.getStorageSync('insurantName');
    if (insurantName === '') {
      insurantName = this.data.insurantName0;
    };

    var insurantPhone = wx.getStorageSync('insurantPhone');
    if (insurantPhone === '') {
      insurantPhone = this.data.insurantPhone0;
    };

    var beneficiaryName = wx.getStorageSync('beneficiaryName');
    if (beneficiaryName === '') {
      beneficiaryName = this.data.beneficiaryName0;
    };

    var invoiceTitle = wx.getStorageSync('invoiceTitle');
    if (invoiceTitle === '') {
      invoiceTitle = this.data.invoiceTitle0;
    }; 

    var safeBuyDate = wx.getStorageSync('safeBuyDate');
    if (safeBuyDate === '') {
      safeBuyDate = this.data.safeBuyDate0;
    };
    var deviceBrand = wx.getStorageSync('deviceBrand');
    if (deviceBrand === '') {
      deviceBrand = this.data.deviceBrand0;
    };

    var address = wx.getStorageSync('address');
    if (address === '') {
      address = this.data.address0;
    };

    var remark = wx.getStorageSync('remark');
    if (remark === '') {
      remark = this.data.remark0;
    };
    
    this.setData({
      applicantName: applicantName,
      applicantId: applicantId,
      insurantName: insurantName,
      insurantPhone: insurantPhone,
      beneficiaryName: beneficiaryName,
      invoiceTitle: invoiceTitle,
      safeBuyDate: safeBuyDate,
      deviceBrand: deviceBrand,
      address: address,
      remark: remark,
    });

  },


  onReady: function () {

  },


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

  },

})