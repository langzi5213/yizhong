// page-safe/safe-message/message.js
Page({

  data: {
    dateList: [
      '请选择投保时长',
      '1 年',
      '2 年',
      '3 年',
      '4 年',
      '5 年',
    ],
    indexTime: 0,



    otherList: [
      '0',
      '10万',
      '20万',
      '30万',
      '40万',
      '50万',
    ],
    otherList1: [
      0,
      100000,
      200000,
      300000,
      400000,
      500000,
    ],
    indexOperation: 0,
    indexOther: 0,

    price: 0,
    feilv0: 0.70,
    feilv1: 0.35,
    feilv2: 1.00,
    feilv3: 0.20,
    feilv4: 0.14,
    feilv5: 0.05,
    feilv6: 0.20,

    price0: 0,
    price1: 0,
    price2: 0,
    price3: 0,
    price4: 0,
    price5: 0,
    price6: 0,

    allprice: 0,
    checked1: false,
    checked2: false,
    checked3: false,
    checked4: false,
    checked5: false,
    checked6: false,


   
  },

  getInputNumber: function (e) {

    wx.setStorageSync('price', e.detail.value);
    this.setData({
      price: e.detail.value,
    });
    this.refresh()
  },
  timeChange: function (e) {
    wx.setStorageSync('indexTime', e.detail.value);
    this.setData({
      indexTime: e.detail.value
    });
    this.refresh()
  },

  otherChange: function (e) {
    wx.setStorageSync('indexOther', e.detail.value);
    this.setData({
      indexOther: e.detail.value,
    });
    this.refresh()
  },
  operationChange: function (e) {
    wx.setStorageSync('indexOperation', e.detail.value);
    this.setData({
      indexOperation: e.detail.value
    });
    this.refresh()
  },






  switchChange1: function (e) {
    wx.setStorageSync('switch1', e.detail.value);
    this.setData({
      checked1: e.detail.value,
      indexOperation: 1,
    });
    this.refresh()
  },

  switchChange2: function (e) {
    wx.setStorageSync('switch2', e.detail.value);
    this.setData({
      checked2: e.detail.value,
      indexOther: 1,
    });
    this.refresh()
  },
  switchChange3: function (e) {
    wx.setStorageSync('switch3', e.detail.value);
    this.setData({
      checked3: e.detail.value,
    });
    this.refresh()
  },
  switchChange4: function (e) {
    wx.setStorageSync('switch4', e.detail.value);
    this.setData({
      checked4: e.detail.value,
    });
    this.refresh()
  },
  switchChange5: function (e) {
    wx.setStorageSync('switch5', e.detail.value);
    this.setData({
      checked5: e.detail.value,
    });
    this.refresh()
  },
  switchChange6: function (e) {
    wx.setStorageSync('switch6', e.detail.value);
    this.setData({
      checked6: e.detail.value,
    });
    this.refresh()
  },
  switchChange7: function (e) {
    wx.setStorageSync('switch7', e.detail.value);
    this.setData({
      checked7: e.detail.value,
    });
    this.refresh()
  },

  submit: function () {

    var applicantName = wx.getStorageSync('applicantName');
    var applicantId = wx.getStorageSync('applicantId');
    var insurantName = wx.getStorageSync('insurantName');
    var insurantPhone = wx.getStorageSync('insurantPhone');
    var beneficiaryName = wx.getStorageSync('beneficiaryName');
    var invoiceTitle = wx.getStorageSync('invoiceTitle');
    var safeBuyDate = wx.getStorageSync('safeBuyDate');
    var deviceBrand = wx.getStorageSync('deviceBrand');
    var address = wx.getStorageSync('address');
    var remark = wx.getStorageSync('remark');

    var price = wx.getStorageSync('price');
    var indexTime = wx.getStorageSync('indexTime');
    var allPrice = wx.getStorageSync('allPrice');
    

    console.log(applicantName);
    console.log(applicantId);
    console.log(insurantName);
    console.log(insurantPhone);
    console.log(beneficiaryName);
    console.log(invoiceTitle);
    console.log(safeBuyDate);
    console.log(deviceBrand);
    console.log(address);
    console.log(remark);

    console.log(price);
    console.log(indexTime);
    console.log(allPrice);
 


    if (price === '') {
      wx.showToast({
        title: '请输入保品价值',
        icon: 'loading'
      })
    } else if (this.data.indexTime === '') {
      wx.showToast({
        title: '请选择投保年数',
        icon: 'loading'
      })
    }else  {
     

    wx.request({
      url: 'https://www.gcjxglzx.com/insure/save', 
      method: 'POST',
      data: {
        
       // insureResValue: price,
        // insureResYears: indexTime,
        // insurePrice: allPrice,

          applicantName: applicantName,
        // identityNum: applicantId,
        // insurantName: insurantName,
        // insurantContact: insurantPhone,
        // beneficiaryName:beneficiaryName,
        // invoiceTitle: invoiceTitle,
        // resBuyDate: safeBuyDate,
        // deviecBrand: deviceBrand,
        

        // plateNum: 'plateNum',
        // enginePlateNum: 'enginePlateNum',
        // warrantReceiptorAddres: address,
        // identityPositiveImage: 'identityPositiveImage',
        // identityOppositeImage: 'identityOppositeImage',
   
      },
      header: {
        'content-type': 'application/x-www-form-urlencoded' // 默认值
      },

      success: function (res) {
        console.log('返回结果success' + res.data.message)
      },
      fail: function (res) {
        console.log('返回结果fail' + res.data.message)
      },
      complete: function (res) {
        console.log('返回结果complete' + res.data.message)
        if (res.data.message === '成功！') {
          wx.showToast({
            title: '提交成功',
            icon: 'success'
          })
          wx.navigateTo({
            url: '/page-safe/safe-image/image'
          })
        }else{
          wx.showToast({
            title: '提交失败',
            icon: 'loading'
          })
        }
      }
    })
   
  }
   
},

  onLoad: function (options) {
    var price = wx.getStorageSync('price');
    var indexTime = wx.getStorageSync('indexTime');
    var indexOther = wx.getStorageSync('indexOther');
    var indexOperation = wx.getStorageSync('indexOperation');


    console.log('niho' + price  + indexTime + indexOther + indexOperation)
    this.setData({
      price: price,
      indexTime: indexTime,
      indexOther: indexOther,
      indexOperation: indexOperation,
      
    });
    this.refresh()
  },
  
  refresh: function () {
    var pp00 = this.data.feilv0 * this.data.indexTime * this.data.price / 100;
    var pp11 = this.data.otherList1[this.data.indexOperation] * this.data.indexTime * this.data.feilv1 / 100;
    var pp22 = this.data.otherList1[this.data.indexOther] * this.data.indexTime * this.data.feilv2 / 100;

    var pp33 = this.data.feilv3 * this.data.indexTime * this.data.price / 100;
    var pp44 = this.data.feilv4 * this.data.indexTime * this.data.price / 100;
    var pp55 = this.data.feilv5 * this.data.indexTime * this.data.price / 100;
    var pp66 = this.data.feilv6 * this.data.indexTime * this.data.price / 100;

    var pp0 = parseFloat(pp00.toFixed(2));
    var pp1 = parseFloat(pp11.toFixed(2));
    var pp2 = parseFloat(pp22.toFixed(2));
    var pp3 = parseFloat(pp33.toFixed(2));
    var pp4 = parseFloat(pp44.toFixed(2));
    var pp5 = parseFloat(pp55.toFixed(2));
    var pp6 = parseFloat(pp66.toFixed(2));

    this.setData({
      price0: pp0,

    });


    if (this.data.checked1 === true) {
      this.setData({
        price1: pp1,
      })
    } else {
      this.setData({
        price1: 0,
        indexOperation: 0

      })
    };

    if (this.data.checked2 === true) {
      this.setData({
        price2: pp2,
      })
    } else if (this.data.checked2 === false) {
      this.setData({
        price2: 0,
        indexOther: 0,
      })
    };
    if (this.data.checked3 === true) {
      this.setData({
        price3: pp3,
      })
    } else {
      this.setData({
        price3: 0,
      })
    };
    if (this.data.checked4 === true) {
      this.setData({
        price4: pp4,
      })
    } else {
      this.setData({
        price4: 0,
      })
    };

    if (this.data.checked5 === true) {
      this.setData({
        price5: pp5,
      })
    } else {
      this.setData({
        price5: 0,
      })
    };

    if (this.data.checked6 === true) {
      this.setData({
        price6: pp6,
      })
    } else {
      this.setData({
        price6: 0,
      })
    };

    var allprice11 = this.data.price1 + this.data.price2 + this.data.price3 + this.data.price4 + this.data.price5 + this.data.price6 + this.data.price0;
    var allprice1 = parseFloat(allprice11.toFixed(2));
    this.setData({
      allprice: allprice1,
    })
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