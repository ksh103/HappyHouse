export default {
  makeDateStr : function(year, month, day, type){
    if( type == '.' ){
      return year + '.' + ( (month < 10) ? '0' + month : month ) + '.' + ( (day < 10) ? '0' + day : day );
    }
  },
  makeTimeStr : function(hour, minute, second, type){
    if( type == ':' ){
      return hour + ':' + ( (minute < 10) ? '0' + minute : minute ) + ':' + ( (second < 10) ? '0' + second : second );
    }
  }
}