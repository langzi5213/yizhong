const app = getApp()
Page({

  data: {
    
    deviceBrand0:'请输入设备品牌',
    deviceType0: '请输入设备类型',
    deviceModel0: '请输入设备型号',
    deviceColor0: '请输入机械颜色',
    engineModel0: '请输入发动机型号',
    enginePower0: '请输入发动机功率',
    enginePlateNum0: '请输入发动机编号',
    produceDate0: 'ssss',
    buyDate0: 'ssss',
    orignPlace0: '请输入设备产地',
    walkWay0: 'ssss',
    fuelType0: 'ssss',
    workHours0: '请输入设备工作小时',
    weight0: '请输入整机重量',
    tailGas0: '请输入尾气排放值',
    deviceNum0: '请输入设备编号',
    produceNum0: '请输入出厂编号',

    deviceBrand: '',
    deviceType: '',
    deviceModel: '',
    deviceColor: '请输入机械颜色',
    engineModel: '请输入发动机型号',
    enginePower: '请输入发动机功率',
    enginePlateNum: '请输入发动机编号',
    produceDate: "2018-01-01",
    buyDate: "2018-01-01",
    orignPlace: '请输入设备产地',
    walkWay: 'ssss',
    fuelType: 'ssss',
    workHours: '请输入设备工作小时',
    weight: '请输入整机重量',
    tailGas: '请输入尾气排放值',
    deviceNum: '请输入设备编号',
    produceNum: '请输入出厂编号',


    date: {
      startDate: "2017-01-01",
      endDate: "2200-01-01",
    },
     

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

  deviceBrand: function (e) {
    wx.setStorageSync('deviceBrand', e.detail.value);
  },
  deviceType: function (e) {
    wx.setStorageSync('deviceType', e.detail.value);
  },
  deviceModel: function (e) {
    wx.setStorageSync('deviceModel', e.detail.value);
  },

  deviceColor: function (e) {
    wx.setStorageSync('deviceColor', e.detail.value);
  }, 
  engineModel: function (e) {
    wx.setStorageSync('engineModel', e.detail.value);
  },
  enginePower: function (e) {
    wx.setStorageSync('enginePower', e.detail.value);
  },
  enginePlateNum: function (e) {
    wx.setStorageSync('enginePlateNum', e.detail.value);
  }, 
  orignPlace: function (e) {
    wx.setStorageSync('orignPlace', e.detail.value);
  }, 
  workHours: function (e) {
    wx.setStorageSync('workHours', e.detail.value);
  }, 
  weight: function (e) {
    wx.setStorageSync('weight', e.detail.value);
  },
  
  tailGas: function (e) {
    wx.setStorageSync('tailGas', e.detail.value);
  }, 
  
  deviceNum: function (e) {
    wx.setStorageSync('deviceNum', e.detail.value);
  },
  produceNum: function (e) {
    wx.setStorageSync('produceNum', e.detail.value);
  },




  submit: function (e) {
   
    var deviceBrand = e.detail.value.deviceBrand
    if (deviceBrand === '') {
      deviceBrand = wx.getStorageSync('deviceBrand');
    }
    var deviceType = e.detail.value.deviceType
    if (deviceType === '') {
      deviceType = wx.getStorageSync('deviceType');
    }
    var deviceModel = e.detail.value.deviceModel
    if (deviceModel === '') {
      deviceModel = wx.getStorageSync('deviceModel');
    }
    var deviceColor = e.detail.value.deviceColor
    if (deviceColor === '') {
      deviceColor = wx.getStorageSync('deviceColor');
    }
    var engineModel = e.detail.value.engineModel
    if (engineModel === '') {
      engineModel = wx.getStorageSync('engineModel');
    }
    var enginePower = e.detail.value.enginePower
    if (enginePower === '') {
      enginePower = wx.getStorageSync('enginePower');
    }
    var enginePlateNum = e.detail.value.enginePlateNum
    if (enginePlateNum === '') {
      enginePlateNum = wx.getStorageSync('enginePlateNum');
    }
    var orignPlace = e.detail.value.orignPlace
    if (orignPlace === '') {
      orignPlace = wx.getStorageSync('orignPlace');
    }
    var workHours = e.detail.value.workHours
    if (workHours === '') {
      workHours = wx.getStorageSync('workHours');
    }
    var weight = e.detail.value.weight
    if (weight === '') {
      weight = wx.getStorageSync('weight');
    }
    var tailGas = e.detail.value.tailGas
    if (tailGas === '') {
      tailGas = wx.getStorageSync('tailGas');
    }

    var deviceNum = e.detail.value.deviceNum
    if (deviceNum === '') {
      deviceNum = wx.getStorageSync('deviceNum');
    }
    var produceNum = e.detail.value.produceNum
    if (produceNum === '') {
      produceNum = wx.getStorageSync('produceNum');
    }

     //不能删
    // if (deviceBrand === '') {
    //   wx.showToast({
    //     title: this.data.deviceBrand,
    //     icon: 'loading'
    //   })
    // } else if (deviceType === '') {
    //   wx.showToast({
    //     title: this.data.deviceType,
    //     icon: 'loading'
    //   })
    // } else if (deviceModel === '') {
    //   wx.showToast({
    //     title: this.data.deviceModel,
    //     icon: 'loading'
    //   })
    // } else if (deviceColor === '') {
    //   wx.showToast({
    //     title: this.data.deviceColor,
    //     icon: 'loading'
    //   })
    // } else if (engineModel === '') {
    //   wx.showToast({
    //     title: this.data.engineModel,
    //     icon: 'loading'
    //   })
    // } else if (enginePower === '') {
    //   wx.showToast({
    //     title: this.data.enginePower,
    //     icon: 'loading'
    //   })
    // } else if (enginePlateNum === '') {
    //   wx.showToast({
    //     title: this.data.enginePlateNum,
    //     icon: 'loading'
    //   })
    // } else if (orignPlace === '') {
    //   wx.showToast({
    //     title: this.data.orignPlace,
    //     icon: 'loading'
    //   })
    // } else if (workHours === '') {
    //   wx.showToast({
    //     title: this.data.workHours,
    //     icon: 'loading'
    //   })
    // } else if (weight === '') {
    //   wx.showToast({
    //     title: this.data.weight,
    //     icon: 'loading'
    //   })
    // } else if (tailGas === '') {
    //   wx.showToast({
    //     title: this.data.tailGas,
    //     icon: 'loading'
    //   })
    // } else if (deviceNum === '') {
    //   wx.showToast({
    //     title: this.data.deviceNum,
    //     icon: 'loading'
    //   })
    // } else if (produceNum === '') {
    //   wx.showToast({
    //     title: this.data.produceNum,
    //     icon: 'loading'
    //   })
    // }else {
    //   wx.navigateTo({
    //     url: '/page-register/reg-image/reg-image'
    //   })
    // }

    wx.navigateTo({
      url: '/page-register/reg-image/reg-image'
    })
    
    // var ownerName = e.detail.value.ownerName
    // var identityNum = e.detail.value.identityNum
    // var buyDate = e.detail.value.buyDate
    // var plateNum = e.detail.value.plateNum
    // var bigShelfNum = e.detail.value.bigShelfNum
    // var enginePlateNum = e.detail.value.enginePlateNum
    // var hydraulicPlateNum = e.detail.value.hydraulicPlateNum
    // var remark = e.detail.value.remark

    // console.log(ownerName);
    // console.log(identityNum);
    // console.log(buyDate);
    // console.log(plateNum);
    // console.log(bigShelfNum);
    // console.log(enginePlateNum);
    // console.log(hydraulicPlateNum);
    // console.log(remark);

    // wx.request({
    //   url: 'http://www.gcjxglzx.com:8071/machinery/save',
    //   data: {
    //     ownerName: ownerName,
    //     identityNum: identityNum,
    //     buyDate: buyDate,
    //     plateNum: plateNum,
    //     bigShelfNum: bigShelfNum,
    //     enginePlateNum: enginePlateNum,
    //     hydraulicPlateNum: hydraulicPlateNum,
    //     remark: remark,
    //   },
    //   header: {
    //     'content-type': 'application/json' // 默认值
    //   },
    //   success: function (res) {
    //     console.log(res.data)
    //   }
    // })

  },
  
  produceChange: function (e) {
    this.setData({
      produceDate: e.detail.value
    })
  },
  buyChange: function (e) {
    this.setData({
      buyDate: e.detail.value
    })
  },
  deviceBrand:function(e){
    var deviceBrand = e.detail.value
    wx.setStorageSync('deviceBrand', e.detail.value);
    
   
  },

  reset: function (e) {
    this.setData({
      deviceBrand: this.data.deviceBrand0,
      deviceType: this.data.deviceType0,
      deviceModel: this.data.deviceModel0,
      enterpriseNum: this.data.enterpriseNum0,
      deviceColor: this.data.deviceColor0,
      engineModel: this.data.engineModel0,
      enginePower: this.data.enginePower0,
      enginePlateNum: this.data.enginePlateNum0,
      orignPlace: this.data.orignPlace0,
      workHours: this.data.workHours0,
      weight: this.data.weight0,
      tailGas: this.data.tailGas0,
      deviceNum: this.data.deviceNum0,
      produceNum: this.data.produceNum0,
    })
    wx.setStorageSync('deviceBrand', '')
    wx.setStorageSync('deviceType', '')
    wx.setStorageSync('deviceModel', '')
    wx.setStorageSync('enterpriseNum', '')
    wx.setStorageSync('deviceColor', '')
    wx.setStorageSync('engineModel', '')
    wx.setStorageSync('enginePower', '')
    wx.setStorageSync('enginePlateNum', '')

    wx.setStorageSync('orignPlace', '')
    wx.setStorageSync('workHours', '')
    wx.setStorageSync('weight', '')
    wx.setStorageSync('tailGas', '')
    wx.setStorageSync('deviceNum', '')
    wx.setStorageSync('produceNum', '')
  },


  onLoad: function (options) {
    var deviceBrand= wx.getStorageSync('deviceBrand');
    if (deviceBrand === '') {
      deviceBrand = this.data.deviceBrand0;
    };
    var deviceType = wx.getStorageSync('deviceType');
    if (deviceType === '') {
      deviceType = this.data.deviceType0;
    };
    var deviceModel = wx.getStorageSync('deviceModel');
    if (deviceModel === '') {
      deviceModel = this.data.deviceModel0;
    };
    var deviceColor = wx.getStorageSync('deviceColor');
    if (deviceColor === '') {
      deviceColor = this.data.deviceColor0;
    };
    var engineModel = wx.getStorageSync('engineModel');
    if (engineModel === '') {
      engineModel = this.data.engineModel0;
    };
    var enginePower = wx.getStorageSync('enginePower');
    if (enginePower === '') {
      enginePower = this.data.enginePower0;
    }; 
    var enginePlateNum = wx.getStorageSync('enginePlateNum');
    if (enginePlateNum === '') {
      enginePlateNum = this.data.enginePlateNum0;
    }; var orignPlace = wx.getStorageSync('orignPlace');
    if (orignPlace === '') {
      orignPlace = this.data.orignPlace0;
    }; var workHours = wx.getStorageSync('workHours');
    if (workHours === '') {
      workHours = this.data.workHours0;
    }; var weight = wx.getStorageSync('weight');
    if (weight === '') {
      weight = this.data.weight0;
    }; var tailGas = wx.getStorageSync('tailGas');
    if (tailGas === '') {
      tailGas = this.data.tailGas0;
    }; var deviceNum = wx.getStorageSync('deviceNum');
    if (deviceNum === '') {
      deviceNum = this.data.deviceNum0;
    }; var produceNum = wx.getStorageSync('produceNum');
    if (produceNum === '') {
      produceNum = this.data.produceNum0;
    };

    

    this.setData({
      deviceBrand: deviceBrand,
      deviceType: deviceType,
      deviceModel: deviceModel,
      deviceColor: deviceColor,
      engineModel: engineModel,
      enginePower: enginePower,
      enginePlateNum: enginePlateNum,
      orignPlace: orignPlace,
      workHours: workHours,
      weight: weight,
      tailGas: tailGas,
      deviceNum: deviceNum,
      produceNum: produceNum,
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