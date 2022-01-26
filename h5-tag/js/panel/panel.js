var com_Swiper1 = null;
var com_Swiper2 = null;
var com_Swiper3 = null;

window.onload = function() {
    /* Mock Render */
    MOCK_render_Swiper1();
    MOCK_render_Swiper2();
    MOCK_render_Swiper3();

    $("#back-btn").click(function (e) {
        window.location.href = "../index.html";
    });
}




// Component
/********************************
 * Swiper 1 
 */
function render_Swiper1(jsondata) {
    var templateContent = doT.template($("#swiper-tmp1").html());
    /* Change Render Id Here */
    $("#swiper1"/*RenderId*/).empty().html(templateContent(jsondata));
    var swiper = new Swiper('#com_swiper1', {
        pagination: '.swiper-pagination',
        paginationClickable: true,
        autoplay: 2500,
        loop: true
    });
}

/**
 * click event on swiper element
 */
function Swiper1_toDetail() {
    /**
     * click event
     */
}








/********************************
 * Swiper 2
 */
function render_Swiper2(jsondata) {
    var templateContent = doT.template($("#swiper-tmp2").html());
    /* Change Render Id Here */
    $("#swiper2"/*RenderId*/).empty().html(templateContent(jsondata));
    com_Swiper2 = new Swiper('#com_swiper2', {
        effect: 'coverflow',
        grabCursor: true,
        centeredSlides: true,
        slidesPerView: 'auto',
        freeMode: true,
        freeModeMomentumRatio: '0',
        coverflow: {
            rotate: 50,
            stretch: 0,
            depth: 100,
            modifier: 1,
        }
    });
}

/**
 * click event on swiper element
 */
 function Swiper2_toDetail() {
    /**
     * click event
     */
}








/********************************
 * Swiper 3
 */
function render_Swiper3(jsondata) {
    
}