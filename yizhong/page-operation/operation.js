// page-shop/operation/operation.js
const app = getApp()
Page({
  data: {
    date: {
      startDate: "1870-01-01",
      endDate: "2018-01-01",
      selectDate: "请选择出生日期",
      // selectCerstart: "",
      // selectCerend: "",
      // selectTrainstart: "",
      // selectTrainend: "",

    },
    educationList: [
      '请选择学历',
      '小学',
      '初中',
      '初中中专',
      '高中',
      '高中中专',
      '大专',
      '本科',
      '硕士研究生',
      '博士研究生'

    ],
    indexEducation: '0',
    name: '请输入姓名',
    idCard: '请输入身份证号',
    company: '请输入工作单位',
    origin: '请输入籍贯',
    address: '请输入通讯地址',
    phoneNumber: '请输入手机号码',
    eMail: '请输入电子邮箱',
    year: '请输入从业年龄',
    remark: '请输入您的备注信息',


    sfzqm: app.globalData.operationImage[0],
    sfzbm: app.globalData.operationImage[1],
    rentou: app.globalData.operationImage[2],

    kindList: [
      '请选择工程机械种类',
      '挖掘机',
      '装载机',
      '矿用自卸车',
      '推土机',
      '压路机',
      '平地机',
      '混凝土机械',
      '桩工机械',
      '凿岩机械',
    ],
    indexKind: '0',

    certificatesList: [
      '请选择办理证件类型',
      '操作证',
      '评估师证',
      '维修技师证',
    ],
    indexCertificates: '0',

    czz: false,
    pgsz: false,
    wxjsz: false,

  },


  birthdayChange: function (e) {
    this.setData({
      selectDate: e.detail.value
    })
  },
  name: function (e) {
    wx.setStorageSync('name', e.detail.value);
    this.setData({
      name: e.detail.value
    })
  },
  idCard: function (e) {
    wx.setStorageSync('idCard', e.detail.value);
    this.setData({
      idCard: e.detail.value
    })
  },
  company: function (e) {
    wx.setStorageSync('company', e.detail.value);
    this.setData({
      company: e.detail.value
    })
  },
  origin: function (e) {
    wx.setStorageSync('origin', e.detail.value);
    this.setData({
      origin: e.detail.value
    })
  },
  address: function (e) {
    wx.setStorageSync('address', e.detail.value);
    this.setData({
      address: e.detail.value
    })
  },
  phoneNumber: function (e) {
    wx.setStorageSync('phoneNumber', e.detail.value);
    this.setData({
      phoneNumber: e.detail.value
    })
  },
  eMail: function (e) {
    wx.setStorageSync('eMail', e.detail.value);
    this.setData({
      eMail: e.detail.value
    })
  },
  year: function (e) {
    wx.setStorageSync('year', e.detail.value);
    this.setData({
      year: e.detail.value
    })
  },
  remark: function (e) {
    wx.setStorageSync('remark', e.detail.value);
    this.setData({
      remark: e.detail.value
    })
  },





  kindChange: function (e) {
    this.setData({
      indexKind: e.detail.value
    })
  },
  certificatesChange: function (e) {
    this.setData({
      indexCertificates: e.detail.value,
    }),
      console.log(e.detail.value)
    if (e.detail.value === '1') {
      this.setData({
        czz: true,
        pgsz: false,
        wxjsz: false
      })
    }
    console.log(this.data.czz)
    if (e.detail.value === '2') {
      this.setData({
        czz: false,
        pgsz: true,
        wxjsz: false
      })
    }
    console.log(this.data.pgsz)
    if (e.detail.value === '3') {
      this.setData({
        czz: false,
        pgsz: false,
        wxjsz: true
      })
    }
    console.log(this.data.wxjsz)
  },

  educationChange: function (e) {
    this.setData({
      indexEducation: e.detail.value
    })
  },

  // cerStartChange: function (e) {
  //   this.setData({
  //     selectCerstart: e.detail.value
  //   })
  // },
  // cerEndChange: function (e) {
  //   this.setData({
  //     selectCerend: e.detail.value
  //   })
  // }, trainStartChange: function (e) {
  //   this.setData({
  //     selectTrainstart: e.detail.value
  //   })
  // },
  // trainEndchenag: function (e) {
  //   this.setData({
  //     selectTrainend: e.detail.value
  //   })
  // },

  submit: function (e) {
    var certificater = e.detail.value.certificate
    var kind = e.detail.value.kind
    var level = e.detail.value.level

    var name
    if (e.detail.value.name != '') {
      name = e.detail.value.name
    } else {
      name = wx.getStorageSync('name');
    }
    var idCard
    if (e.detail.value.idCard != '') {
      idCard = e.detail.value.idCard
    } else {
      idCard = wx.getStorageSync('idCard');
    }
    var company
    if (e.detail.value.company != '') {
      company = e.detail.value.company
    } else {
      company = wx.getStorageSync('company');
    }

    var sex = e.detail.value.sex
    var birthday = e.detail.value.birthday
    var education = e.detail.value.education

    var origin
    if (e.detail.value.origin != '') {
      origin = e.detail.value.origin
    } else {
      origin = wx.getStorageSync('origin');
    }

    var origin
    if (e.detail.value.origin != '') {
      origin = e.detail.value.origin
    } else {
      origin = wx.getStorageSync('origin');
    }

    var address
    if (e.detail.value.address != '') {
      address = e.detail.value.address
    } else {
      address = wx.getStorageSync('origin');
    }
    var phoneNumber
    if (e.detail.value.phoneNumber != '') {
      phoneNumber = e.detail.value.phoneNumber
    } else {
      phoneNumber = wx.getStorageSync('phoneNumber');
    }
    var eMail
    if (e.detail.value.eMail != '') {
      eMail = e.detail.value.eMail
    } else {
      eMail = wx.getStorageSync('eMail');
    }
    var year
    if (e.detail.value.year != '') {
      year = e.detail.value.year
    } else {
      year = wx.getStorageSync('year');
    } var remark



    // var cerStartdata = e.detail.value.cerStartdata
    // var cerEnddata = e.detail.value.cerEnddata
    // var trainStartdata = e.detail.value.trainStartdata
    // var trainEnddata = e.detail.value.trainEnddata
    if (e.detail.value.remark != '') {
      remark = e.detail.value.remark
    } else {
      remark = wx.getStorageSync('remark');
    }

    if (certificater === '0') {
      wx.showToast({
        title: '请输办证类型',
        icon: 'loading'
      })
    } else if (name === '') {
      wx.showToast({
        title: '请输入姓名',
        icon: 'loading'
      })
    } else if (idCard === '') {
      wx.showToast({
        title: '请输入身份证号',
        icon: 'loading'
      })
    } else if (idCard.length != 18) {
      wx.showToast({
        title: '请输入正确的身份证号',
        icon: 'loading'
      })
    } else if (company === '') {
      wx.showToast({
        title: '请选择工作单位',
        icon: 'loading'
      })
    } else if (origin === '') {
      wx.showToast({
        title: '请输入籍贯',
        icon: 'loading'
      })
    } else if (address === '') {
      wx.showToast({
        title: '请输入地址',
        icon: 'loading'
      })
    } else if (phoneNumber === '') {
      wx.showToast({
        title: '请输入手机号',
        icon: 'loading'
      })
    } else if (phoneNumber.length != 11) {
      wx.showToast({
        title: '请输入正确的手机号',
        icon: 'loading'
      })
    } else if (year === '') {
      wx.showToast({
        title: '请选择从业年龄',
        icon: 'loading'
      })
    } else {
      


    console.log(sex);
    console.log(certificater);
    console.log(kind);
    console.log(level);
    console.log(name);
    
    console.log(birthday);
    console.log(education);
    console.log(company);
    console.log(origin);
    console.log(address);
    console.log(phoneNumber);
    console.log(eMail);
    console.log(year); 
    console.log(remark);

    wx.request({
      url: 'https://www.gcjxglzx.com/handCertificateBasic/save',
      method:'POST',
      data: {
        
        certificateType: certificater,
        machinerytype: kind,
        certificatelevel: level,
        name: name,
        sex: sex,
        // identityNum: 'identityNum',
        birthday: birthday,
        educationLevel: education,
        companyName: company,
        origin: origin,
        addres: address,
        phone: phoneNumber,
        email: eMail,
        workTime: year,
        remark:remark,
       },
      
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },
      success: function (res) {
        console.log('返回结果success' + res.data.message)
      },
      fail: function(res){
        console.log('返回结果fail' + res.data.message)
      },
      complete:function(res){
        console.log('返回结果complete' + res.data.message)
        if (res.data.message ==='成功！'){
          wx.showToast({
            title: '提交成功',
            icon: 'success'
          })
        }
      }
    })
 }
       
    // var sfzqm = this.data.sfzqm
    // var sfzbm = this.data.sfzbm
    // var rentou = this.data.rentou
    //     wx.uploadFile({
    //       url: 'https://www.gcjxglzx.com/handCertificateBasic/save', 
    //       filePath: rentou[0],
    //       name: 'file',
    //       formData: {
    //         'user': 'test'
    //       },
    //       success: function (res) {
    //         var data = res.data
    //         console.log('传照片成功')
    //         //do something
    //       }
    //     })
    
    // app.uploadimg({
    //   // id: res.data.data,
    //   url: 'https://www.gcjxglzx.com/handCertificateBasic/save',
    //   path: app.globalData.operationImage,
    // })
    // wx.showToast({
    //   title: '提交成功',
    //   icon: 'success',
    //   duration: 2000
    // })   
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
        app.globalData.operationImage[e.target.id] = res.tempFilePaths
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
            rentou: res.tempFilePaths
          })
        }
      }
    });
  },

  onLoad: function (options) {
    var name = wx.getStorageSync('name');
    var idCard = wx.getStorageSync('idCard');
    var company = wx.getStorageSync('company');
    var origin = wx.getStorageSync('origin');
    var address = wx.getStorageSync('address');
    var phoneNumber = wx.getStorageSync('phoneNumber');
    var eMail = wx.getStorageSync('eMail');
    var year = wx.getStorageSync('year');
    var remark = wx.getStorageSync('remark');


    this.setData({

      name: name,
      idCard: idCard,
      company: company,
      origin: origin,
      address: address,
      phoneNumber: phoneNumber,
      eMail: eMail,

      year: year,
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