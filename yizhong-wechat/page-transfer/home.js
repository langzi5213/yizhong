const app = getApp()
const date = new Date()
const provinceList = []
const letterList = []

for (let i = 1990; i <= date.getFullYear(); i++) {
  provinceList.push(i)
}

for (let i = 1; i <= 12; i++) {
  letterList.push(i)
}
Page({

  data: {
    ownerName: '请输入设备转出人姓名',
    ownerId: '请输入转出人身份证号',
    enterpriseName: '请输入企业联系人姓名',
    enterpriseNum: '请输入企业代码',
    spouseName: '请输入转出人配偶姓名',
    spouseId: '请输入配偶身份证号码',
    ownerPhone: '请输入转出人联系电话',
    regAddress: '请输入您的有效通讯地址',

    qylxr: false,

    sliderList: [
      { selected: true, imageSource: '/images/advertisement1.jpg' },
      { selected: false, imageSource: '/images/advertisement2.jpg' },
      { selected: false, imageSource: '/images/advertisement1.jpg' },
    ],
    provinceList: [
      '京', '津', '黑', '吉', '辽', '冀', '豫', '鲁', '晋', '陕', '蒙', '宁', '新', '青', '藏', '鄂', '皖', '苏', '沪', '浙', '闵', '湘', '赣', '川', '渝', '贵', '云', '粤', '桂', '琼', '港', '澳', '台'
    ],
    province: '蒙',

    letterList: [
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    ],
    letter: 'A',
    proviceLetter: false,

    queryName: '张三',
    queryPhone: '15540152534',

    queryTrue: false,
    query: true,

    queryNext: false,
  },


  provinceChange: function (e) {
    this.setData({
      proviceLetter: true,
      query: false,
    });
  },
  provinceChange1: function (e) {
    this.setData({
      proviceLetter: false,
      query: true,
    });
  },
  proviceLetterChange: function (e) {
    const val = e.detail.value
    this.setData({
      province: this.data.provinceList[val[0]],
      letter: this.data.letterList[val[1]],

    })
  },
  query: function (e) {
    this.setData({
      queryTrue: true,
      query: false,
    });
  },
  queryNext: function (e) {
    this.setData({
      queryNext: true,
    });
  },
  nextIn: function () {
    wx.navigateTo({
      url: '/page-transfer/tran-in/tran-in'
    })
  },

  tranChange: function (e) {
    wx.setStorageSync('transfer-radio', e.detail.value);
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
    if (e.detail.value === '') {
      this.setData({
        ownerName: "请输入设备转出人姓名",
      });
    }
    else {
      this.setData({
        ownerName: e.detail.value,
      });
    }
  },
  ownerId: function (e) {
    wx.setStorageSync('ownerId', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        ownerId: "请输入转出人身份证号",
      });
    }
    else {
      this.setData({
        ownerId: e.detail.value,
      });
    }
  },
  enterpriseName: function (e) {
    wx.setStorageSync('enterpriseName', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        enterpriseName: "请输入企业联系人姓名",
      });
    }
    else {
      this.setData({
        enterpriseName: e.detail.value,
      });
    }
  },
  enterpriseNum: function (e) {
    wx.setStorageSync('enterpriseNum', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        enterpriseNum: "请输入企业代码",
      });
    }
    else {
      this.setData({
        enterpriseNum: e.detail.value,
      });
    }
  },
  spouseName: function (e) {
    wx.setStorageSync('spouseName', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        spouseName: "请输入转出人配偶姓名",
      });
    }
    else {
      this.setData({
        spouseName: e.detail.value,
      });
    }
  },
  spouseId: function (e) {
    wx.setStorageSync('spouseId', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        spouseId: "请输入配偶身份证号码",
      });
    }
    else {
      this.setData({
        spouseId: e.detail.value,
      });
    }
  },
  ownerPhone: function (e) {
    wx.setStorageSync('ownerPhone', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        ownerPhone: "请输入转出人联系电话",
      });
    }
    else {
      this.setData({
        ownerPhone: e.detail.value,
      });
    }
  },
  regAddress: function (e) {
    wx.setStorageSync('regAddress', e.detail.value);
    if (e.detail.value === '') {
      this.setData({
        regAddress: "请输入您的有效通讯地址",
      });
    }
    else {
      this.setData({
        regAddress: e.detail.value,
      });
    }
  },


  onLoad: function (options) {
    var ownerName = wx.getStorageSync('ownerName');
    if (ownerName === '') {
      ownerName = "请输入设备转出人姓名";
    };
    var ownerId = wx.getStorageSync('ownerId');
    if (ownerId === '') {
      ownerId = "请输入转出人身份证号";
    };
    var enterpriseName = wx.getStorageSync('enterpriseName');
    if (enterpriseName === '' || enterpriseName === null) {
      enterpriseName = "请输入企业联系人姓名";
    };
    var enterpriseNum = wx.getStorageSync('enterpriseNum');
    if (enterpriseNum === '') {
      enterpriseNum = "请输入企业代码";
    };

    var spouseName = wx.getStorageSync('spouseName');
    if (spouseName === '') {
      spouseName = "请输入转出人配偶姓名";
    };
    var spouseId = wx.getStorageSync('spouseId');
    if (spouseId === '') {
      spouseId = "请输入配偶身份证号码";
    };
    var ownerPhone = wx.getStorageSync('ownerPhone');
    if (ownerPhone === '') {
      ownerPhone = "请输入转出人联系电话";
    };
    var regAddress = wx.getStorageSync('regAddress');
    if (regAddress === '') {
      regAddress = "请输入您的有效通讯地址";
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
  // submit: function (e) {

  //   var ownerName = e.detail.value.ownerName
  //   var identityNum = e.detail.value.identityNum
  //   var buyDate = e.detail.value.buyDate
  //   var plateNum = e.detail.value.plateNum
  //   var bigShelfNum = e.detail.value.bigShelfNum
  //   var enginePlateNum = e.detail.value.enginePlateNum
  //   var hydraulicPlateNum = e.detail.value.hydraulicPlateNum
  //   var remark = e.detail.value.remark

  //   console.log(ownerName);
  //   console.log(identityNum);
  //   console.log(buyDate);
  //   console.log(plateNum); 
  //   console.log(bigShelfNum); 
  //   console.log(enginePlateNum); 
  //   console.log(hydraulicPlateNum);
  //   console.log(remark);

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

  // },
})