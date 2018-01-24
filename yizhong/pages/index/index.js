//index.js
//获取应用实例
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
     sliderList: [
      { selected: true, imageSource: '/images/advertisement1.jpg' },
      { selected: false, imageSource: '/images/advertisement2.jpg' },
      { selected: false, imageSource: '/images/advertisement1.jpg' },
    ],

    newArray: [
      { id: 1, url: '/images/zhengchesfz.jpg', text: '这是第一个新闻' },
      { id: 2, url: '/images/youqianfang.jpg', text: '这是第二个新闻' },
      { id: 3, url: '/images/quanchesfz.jpg', text: '这是第三个新闻' },
      { id: 4, url: '/images/nameplate.jpg', text: '这是第四个新闻' },
      { id: 5, url: '/images/rentou.jpg', text: '这是第五个新闻' },
      { id: 6, url: '/images/banking.png', text: '这是第六个新闻' },
    ],

    imageNew: '/images/new.png',
    imagePeople: '/images/people.png',
    imageMonitor: '/images/monitor.png',
    imageShop: '/images/shop.png',
    imagebutback: '/images/buttonbackground.png',
    imageMonitor: '/images/monitor.png',
    imageBank: '/images/banking.png',
    imageSafe: '/images/safe.png',
    imageOperation: '/images/operation.png',
    imageTransfer: '/images/transfer.png',


   
    certificatesList: [
      '证件号',
      '车牌号',
    ],
    indexCertificates: '0',

    provinceList: [
      '京', '津', '黑', '吉', '辽', '冀', '豫', '鲁', '晋', '陕', '蒙', '宁', '新', '青', '藏', '鄂', '皖', '苏', '沪', '浙', '闵', '湘', '赣', '川', '渝', '贵', '云', '粤', '桂', '琼', '港', '澳','台'
    ],
    province: '蒙',

    letterList: [
      'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',
    ],
    letter: 'A',

    proviceLetter:false,
    zjh: true,
    cph: false,
  },

  provinceChange:function(e){
    this.setData({
      proviceLetter:true,
    });
  },
  provinceChange1: function (e) {
    this.setData({
      proviceLetter: false,
    });
    
  },

  bindChange: function (e) {
    const val = e.detail.value
    this.setData({
      province: this.data.provinceList[val[0]],
      letter: this.data.letterList[val[1]],
      
    })
  },

  newsFunction: function () {
    wx.navigateTo({
      url: '/page-news/news'
    })
  },

  certificatesChange: function (e) {
    var indexCertificates = e.detail.value
    this.setData({
      indexCertificates: e.detail.value,
    })
    if (indexCertificates==0){
      this.setData({
        zjh: true,
        cph:false,
      })
    }
    if (indexCertificates == 1) {
      this.setData({
        cph: true,
        zjh:false,
      })
    }
  },
  personnelFunction: function () {
    wx.navigateTo({
      url: '/page-personnel/home'
    })
  },
  shopFunction: function () {
    wx.navigateTo({
      url: '/page-shop/home'
    })
  },
  monitorFunction: function () {
    wx.navigateTo({
      url: '/page-monitor/home'
    })
  },
  bankingFunction: function () {
    wx.navigateTo({
      url: '/page-banking/home'
    })
  },
  transferFunction: function () {
    wx.navigateTo({
      url: '/page-transfer/home'
    })
  },
  safeFunction: function () {
    wx.navigateTo({
      url: '/page-safe/home'
    })
  },
  operationFunction: function () {
    wx.navigateTo({
      url: '/page-operation/operation'
    })
  },
  registerFunction: function () {
    wx.navigateTo({
      url: '/page-register/home'
    })
  },





  touchdown: function () {
    console.log("手指按下了...")
    console.log("new date : " + new Date)
    var _this = this;
    speaking.call(this);
    this.setData({
      isSpeaking: true
    })

    wx.startRecord({
      success: function (res) {

        var tempFilePath = res.tempFilePath
        console.log("tempFilePath: " + tempFilePath)

        wx.saveFile({
          tempFilePath: tempFilePath,
          success: function (res) {

            var savedFilePath = res.savedFilePath
            console.log("savedFilePath: " + savedFilePath)
          }
        })
        wx.showToast({
          title: '恭喜!录音成功',
          icon: 'success',
          duration: 1000
        })

        wx.getSavedFileList({
          success: function (res) {
            var voices = [];
            for (var i = 0; i < res.fileList.length; i++) {

              var createTime = new Date(res.fileList[i].createTime)

              var size = (res.fileList[i].size / 1024).toFixed(2);
              var voice = { filePath: res.fileList[i].filePath, createTime: createTime, size: size };
              console.log("文件路径: " + res.fileList[i].filePath)
              console.log("文件时间: " + createTime)
              console.log("文件大小: " + size)
              voices = voices.concat(voice);
            }
            _this.setData({
              voices: voices
            })
          }
        })
      },
      fail: function (res) {

        wx.showModal({
          title: '提示',
          content: '录音的姿势不对!',
          showCancel: false,
          success: function (res) {
            if (res.confirm) {
              console.log('用户点击确定')
              return
            }
          }
        })
      }
    })
  },

  touchup: function () {
    console.log("手指抬起了...")
    this.setData({
      isSpeaking: false,
    })
    clearInterval(this.timer)
    wx.stopRecord()
  },





  onLoad: function (options) {

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


  onShareAppMessage: function () {

  }
})
function speaking() {
  var _this = this;
  //话筒帧动画  
  var i = 1;
  this.timer = setInterval(function () {
    i++;
    i = i % 5;
    _this.setData({
      j: i
    })
  }, 200);
}