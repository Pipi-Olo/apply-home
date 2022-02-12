let page_links = document.querySelectorAll(".page-link");
for (let i = 0; i < page_links.length; i++) {
    page_links[i].addEventListener("click", function () {
        let href = window.location.href;
        if (href.indexOf("/index") === -1) {
            href += "index";
        }

        let pageNo = this.getAttribute('data-dt-idx') - 1;
        if (pageNo < 0) {
            pageNo = 0;
        }

        if (href.indexOf('page=') === -1) {
            location.href = href + "?page=" + pageNo.toString();
        }
        else {
            let split = href.split("page=");
            location.href = split[0] + "page=" + pageNo.toString();
        }
    });
}