
$(document).ready(function () {
  $(".owl-carousel").owlCarousel({
    items: 4,
    loop: true,
    autoplay: true,
    autoplayTimeout: 2000,
    autoplayHoverPause: true,
    responsive: {
      0: {
        items: 1
      },
      600: {
        items: 2
      },
      1000: {
        items: 4
      }
    },
    onInitialized: function () {
      var index = 0;
      $('.owl-item').removeClass('active');
      $('.owl-item').eq(index).addClass('active');
      setInterval(function () {
        index++;
        if (index > 3) {
          index = 0;
        }
        $('.owl-item').removeClass('active');
        $('.owl-item').eq(index).addClass('active');
      }, 2000);
    }
  });
});
