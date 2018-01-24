const app = getApp()
Page({

  data: {
    ownerName0: '请输入设备申请人姓名',
    ownerId0: '请输入机主身份证号码',
    enterpriseName0: '请输入企业联系人姓名',
    enterpriseNum0: '请输入企业代码',
    spouseName0: '请输入申请人配偶姓名',
    spouseId0: '请输入配偶身份证号码',
    ownerPhone0: '请输入申请人联系电话',
    regAddress0: '请输入您的有效通讯地址',
  
    ownerName: '请输入设备申请人姓名',
    ownerId: '请输入机主身份证号码',
    enterpriseName: '请输入企业联系人姓名',
    enterpriseNum: '请输入企业代码',
    spouseName: '请输入申请人配偶姓名',
    spouseId: '请输入配偶身份证号码',
    ownerPhone: '请输入申请人联系电话',
    regAddress: '请输入您的有效通讯地址',
    
    qylxr: false,
  },

  registerChange: function (e) {
    wx.setStorageSync('register-radio', e.detail.value);
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
  ownerName: function (e) {
    wx.setStorageSync('ownerName', e.detail.value);
  },
  ownerId: function (e) {
    wx.setStorageSync('ownerId', e.detail.value);
  },
  enterpriseName: function (e) {
    wx.setStorageSync('enterpriseName', e.detail.value);
  },
  enterpriseNum: function (e) {
    wx.setStorageSync('enterpriseNum', e.detail.value);
  },
  spouseName: function (e) {
    wx.setStorageSync('spouseName', e.detail.value);
  },
  spouseId: function (e) {
    wx.setStorageSync('spouseId', e.detail.value);
  },
  ownerPhone: function (e) {
    wx.setStorageSync('ownerPhone', e.detail.value);
  },
  regAddress: function (e) {
    wx.setStorageSync('regAddress', e.detail.value);
  },

  submit: function (e) {

    var ownerName = e.detail.value.ownerName
    var ownerId = e.detail.value.ownerId
    var enterpriseName = e.detail.value.enterpriseName
    var enterpriseNum = e.detail.value.enterpriseNum
    var spouseName = e.detail.value.spouseName
    var spouseId = e.detail.value.spouseId
    var ownerPhone = e.detail.value.ownerPhone
    var regAddress = e.detail.value.regAddress

    if (ownerName === '') {
      ownerName = wx.getStorageSync('ownerName');
    }
    
    if (ownerId === '') {
      ownerId = wx.getStorageSync('ownerId');
    }
    
    if (enterpriseName === '') {
      enterpriseName = wx.getStorageSync('enterpriseName');
    }
    
    if (enterpriseNum === '') {
      enterpriseNum = wx.getStorageSync('enterpriseNum');
    }

    if (spouseName === '') {
      spouseName = wx.getStorageSync('spouseName');
    }
    
    if (spouseId === '') {
      spouseId = wx.getStorageSync('spouseId');
    }
    
    if (ownerPhone === '') {
      ownerPhone = wx.getStorageSync('ownerPhone');
    }

    
    if (regAddress === '') {
      regAddress = wx.getStorageSync('regAddress');
    }

    // if (ownerName === '') {
    //   wx.showToast({
    //     title: this.data.ownerName,
    //     icon: 'loading'
    //   })
    // } else if (ownerId === '') {
    //   wx.showToast({
    //     title: this.data.ownerId,
    //     icon: 'loading'
    //   })
    // } else if (enterpriseName === '') {
    //   wx.showToast({
    //     title: this.data.enterpriseName,
    //     icon: 'loading'
    //   })
    // } else if (enterpriseNum === '') {
    //   wx.showToast({
    //     title: this.data.enterpriseNum,
    //     icon: 'loading'
    //   })
    // } else if (spouseName === '') {
    //   wx.showToast({
    //     title: this.data.spouseName,
    //     icon: 'loading'
    //   })
    // } else if (spouseId === '') {
    //   wx.showToast({
    //     title: this.data.spouseId,
    //     icon: 'loading'
    //   })
    // } else if (ownerPhone === '') {
    //   wx.showToast({
    //     title: this.data.ownerPhone,
    //     icon: 'loading'
    //   })
    // } else if (regAddress === '') {
    //   wx.showToast({
    //     title: this.data.regAddress,
    //     icon: 'loading'
    //   })
    // }else {
    //   wx.navigateTo({
    //     url: '/page-register/reg-message/reg-message'
    //   })
    // }

    wx.navigateTo({
      url: '/page-register/reg-message/reg-message'
    })

    console.log(ownerName);
    console.log(ownerId);
    console.log(enterpriseName);
    console.log(enterpriseNum);
    console.log(spouseName);
    console.log(spouseId);
    console.log(ownerPhone);
    console.log(regAddress);

    //   wx.request({
    //     url: 'http://www.gcjxglzx.com:8071/machinery/save',
    //     data: {
    //       ownerName: ownerName,
    //       identityNum: identityNum,
    //       buyDate: buyDate,
    //       plateNum: plateNum,
    //       bigShelfNum: bigShelfNum,
    //       enginePlateNum: enginePlateNum,
    //       hydraulicPlateNum: hydraulicPlateNum,
    //       remark: remark,
    //     },
    //     header: {
    //       'content-type': 'application/json' // 默认值
    //     },
    //     success: function (res) {
    //       console.log(res.data)
    //     }
    //   })

  },

  reset:function(e){
   this.setData({
      ownerName: this.data.ownerName0,
      ownerId: this.data.ownerId0,
      enterpriseName: this.data.enterpriseName0,
      enterpriseNum: this.data.enterpriseNum0,
      spouseName: this.data.spouseName0,
      spouseId: this.data.spouseId0,
      ownerPhone: this.data.ownerPhone0,
      regAddress: this.data.regAddress0,
    })
   wx.setStorageSync('ownerName', '')
   wx.setStorageSync('ownerId', '')
   wx.setStorageSync('enterpriseName', '')
   wx.setStorageSync('enterpriseNum', '')
   wx.setStorageSync('spouseName', '')
   wx.setStorageSync('spouseId', '')
   wx.setStorageSync('ownerPhone', '')
   wx.setStorageSync('regAddress', '')
  },

  onLoad: function (options) {
    var ownerName = wx.getStorageSync('ownerName');
    if (ownerName === '') {
      ownerName = this.data.ownerName0;
    };
    var ownerId = wx.getStorageSync('ownerId');
    if (ownerId === '') {
      ownerId = this.data.ownerId0;
    };
    var enterpriseName = wx.getStorageSync('enterpriseName');
    if (enterpriseName === '') {
      enterpriseName = this.data.enterpriseName0;
    };
    var enterpriseNum = wx.getStorageSync('enterpriseNum');
    if (enterpriseNum === '') {
      enterpriseNum = this.data.enterpriseNum0;
    };

    var spouseName = wx.getStorageSync('spouseName');
    if (spouseName === '') {
      spouseName = this.data.spouseName0;
    };
    var spouseId = wx.getStorageSync('spouseId');
    if (spouseId === '') {
      spouseId = this.data.spouseId0;
    };
    var ownerPhone = wx.getStorageSync('ownerPhone');
    if (ownerPhone === '') {
      ownerPhone = this.data.ownerPhone0;
    };
    var regAddress = wx.getStorageSync('regAddress');
    if (regAddress === '') {
      regAddress = this.data.regAddress0;
    };
     
    this.setData({
      ownerName: ownerName,
      ownerId: ownerId,
      enterpriseName: enterpriseName,
      enterpriseNum: enterpriseNum,
      spouseName: spouseName,
      spouseId: spouseId,
      ownerPhone: ownerPhone,
      regAddress: regAddress,
     
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

  },

})