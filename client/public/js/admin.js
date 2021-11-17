/*
* Admin Layout (Project Tracker)
* @requires: jQuery v3.0 or later
* @author: WrapTheme
* @design by: ThemeMakker Infotech LLP.
* @event-namespace: PT
* Copyright 2021 WrapTheme
*/

if (typeof jQuery === "undefined") {
  throw new Error("jQuery plugins need to be before this file");
}

$(function () {
  "use strict";
  skinChanger();
  FontSetting();
  $.pt.rightSideBar.activate();

  // $(".js-right-sidebar").on("click", function () {
  //   $(".right-sidebar").toggleClass("open");
  // });
});
$.pt = {};

/* Right Sidebar - Function */
$.pt.rightSideBar = {
  activate: function () {
    var _this = this;
    var $sidebar = $("#rightsidebar");
    var $overlay = $(".overlay");

    //Close sidebar
    $(window).on("click", function (e) {
      var $target = $(e.target);
      if (e.target.nodeName.toLowerCase() === "i") {
        $target = $(e.target).parent();
      }

      if (
        !$target.hasClass("js-right-sidebar") &&
        _this.isOpen() &&
        $target.parents("#rightsidebar").length === 0
      ) {
        if (!$target.hasClass("bars")) $overlay.fadeOut();
        $sidebar.removeClass("open");
      }
    });

    $(".js-right-sidebar").on("click", function () {
      $sidebar.toggleClass("open");
      if (_this.isOpen()) {
        $overlay.fadeIn();
      } else {
        $overlay.fadeOut();
      }
    });
  },
  isOpen: function () {
    // alert("Hello");
    return $(".right-sidebar").hasClass("open");
  },
};

//Skin changer
function skinChanger() {
  $(".right-sidebar .choose-skin li").on("click", function () {
    var $body = $("#layout");
    var $this = $(this);

    var existTheme = $(".right-sidebar .choose-skin li.active").data("theme");
    $(".right-sidebar .choose-skin li").removeClass("active");
    $body.removeClass("theme-" + existTheme);
    $this.addClass("active");
    $body.addClass("theme-" + $this.data("theme"));
  });
}

// Font Setting
function FontSetting() {
  $(".font_setting input:radio").click(function () {
    var others = $("[name='" + this.name + "']").map(function () {
        return this.value;
      }).get().join(" ");
    $("body").removeClass(others).addClass(this.value);
  });
}

// LTR/RTL active js
$('.theme-rtl input:checkbox').on('click', function () {
    if($(this).is(":checked")) {
        $('body').addClass("rtl_mode");
    } else {
        $('body').removeClass("rtl_mode");
    }
});

// light and dark theme setting js
var toggleSwitch = document.querySelector('.theme-switch input[type="checkbox"]');
var toggleHcSwitch = document.querySelector('.theme-high-contrast input[type="checkbox"]');
var currentTheme = localStorage.getItem("theme");
if (currentTheme) {
  document.documentElement.setAttribute("data-theme", currentTheme);

  if (currentTheme === "dark") {
    toggleSwitch.checked = true;
  }
  if (currentTheme === "high-contrast") {
    toggleHcSwitch.checked = true;
    toggleSwitch.checked = false;
  }
}
function switchTheme(e) {
  if (e.target.checked) {
    document.documentElement.setAttribute("data-theme", "dark");
    localStorage.setItem("theme", "dark");
    $('.theme-high-contrast input[type="checkbox"]').prop("checked", false);
  } else {
    document.documentElement.setAttribute("data-theme", "light");
    localStorage.setItem("theme", "light");
  }
}
function switchHc(e) {
  if (e.target.checked) {
    document.documentElement.setAttribute("data-theme", "high-contrast");
    localStorage.setItem("theme", "high-contrast");
    $('.theme-switch input[type="checkbox"]').prop("checked", false);
  } else {
    document.documentElement.setAttribute("data-theme", "light");
    localStorage.setItem("theme", "light");
  }
}
toggleSwitch.addEventListener("change", switchTheme, false);
toggleHcSwitch.addEventListener("change", switchHc, false);

// Prevents menu from closing when clicked inside header menu
document.getElementById("NotificationsDiv").addEventListener('click', function (event) {
  event.stopPropagation(); 
});

// DateRange
$(function () {
  $('#daterange').daterangepicker({
    opens: 'left',
    format: 'MM/YYYY',
    showDropdowns: true,
  }, function (start, end, label) {
    console.log("A new date selection was made: " + start.format('YYYY-MM') + ' to ' + end.format('YYYY-MM'));
  });
});