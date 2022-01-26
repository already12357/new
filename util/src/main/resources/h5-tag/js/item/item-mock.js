/* Mock Button6 */
function MOCK_load_Button6() {
    var mockData = {
        "contentList" : [
            "111111111111111111", 
            "211111111111111", 
            "344444444", 
            "4444444444444", 
            "5",
            "64444444444",
            "7444444444",
            "4444444444448"
        ],
        "total" : 7,
    };

    load_Button6(mockData, ".button-div-6", "#button-tmp6");
}


/* Mock Icon2 */
function MOCK_load_Icon2() {
    load_Icon2('0', '../img/common/choose/choose1/choose1-chosen.png', '../img/common/choose/choose1/choose1.png');
}

/* Mock Icon4 */
function MOCK_load_Icon4() {
    load_Icon4(".icon-style-4", 5, 0.25);
}