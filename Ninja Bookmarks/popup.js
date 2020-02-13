let link =null;
chrome.tabs.getSelected(null, function(tab) {
    Display_set(tab.url);
    link = tab.url;
});
chrome.tabs.getSelected(null ,function(title){
    Display_set_title(title.title);
});

function Display_set(tablink) {
document.getElementById("link_display").value=tablink;

}
function Display_set_title(tabTitle) {
    document.getElementById("title_display").value=tabTitle;
    
    }
   



