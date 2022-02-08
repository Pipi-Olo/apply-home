let page_links = document.querySelectorAll(".page-link");
for (let i = 0; i < page_links.length; i++) {
    page_links[i].addEventListener("click", function () {
        let href = "http://localhost:8080/index";
        let pageNo = this.getAttribute('data-dt-idx') - 1;
        if (pageNo < 0) {
            pageNo = 0;
        }

        location.href = href + "?page=" + pageNo.toString();
    });
}