var com_Swiper1 = null;
var com_Swiper2 = null;
var com_Swiper3 = null;

/* Mock Swiper1 */
function MOCK_render_Swiper1() {
    var templateContent = doT.template($("#swiper-tmp1").html());
    var mockData = {
        "imglist" : [
            "../img/panel/picture1.png", 
            "../img/panel/picture2.png", 
            "../img/panel/picture1.png", 
            "../img/panel/picture2.png"],
    
    };
    $(".swiper1").empty().html(templateContent(mockData));
    com_Swiper1 = new Swiper('#com_swiper1', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        loop: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });
}



/* Mock Swiper2 */
function MOCK_render_Swiper2() {
    var templateContent = doT.template($("#swiper-tmp2").html());
    var mockData = {
        "imglist" : [
            "../img/panel/picture1.png", 
            "../img/panel/picture2.png", 
            "../img/panel/picture1.png", 
            "../img/panel/picture2.png"],
    
    };
    $(".swiper2").empty().html(templateContent(mockData));
    com_Swiper2 = new Swiper('#com_swiper2', {
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: 'auto',
        freeMode: true,
        freeModeMomentumRatio: '0',
        watchSlidesProgress : true,
        coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
        }
    });
}



/* Mock Swiper3 */
function MOCK_render_Swiper3() {
    var templateContent = doT.template($("#swiper-tmp3").html());
    var mockData = {
        "" : [],
        "" : []
    };

    // $(".swiper3").empty().html(templateContent(mockData));

    // com_Swiper3 = new Swiper('#text_div', {
    //     effect: 'coverflow',
    //     centeredSlides: true,
    //     slidesPerView: 'auto',
    //     freeMode: true,
    //     freeModeMomentumRatio: '0',
    //     watchSlidesProgress : true,
    //     coverflow: {
    //         rotate: 50,
    //         stretch: 0,
    //         depth: 100,
    //         modifier: 1,
    //     }
    // });

    com_Swiper3 = new Swiper('#text_swiper', {
        pagination: '.text_swiper_pagniation',
        paginationClickable: true,
        loop: true,
        autoplay: 2500,
        autoplayDisableOnInteraction: false
    });
}