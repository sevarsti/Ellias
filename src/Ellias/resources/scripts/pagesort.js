         function goFirstPage(src)
        {
            src.form['queryForm.page'].value = 1;
            src.form.submit();
        }
        function goLastPage(src)
        {
            src.form['queryForm.page'].value = parseInt(src.form.totalPageCount.value);
            src.form.submit();
        }
        function goNextPage(src)
        {
            src.form['queryForm.page'].value = parseInt(src.form['queryForm.page'].value)+1;
            src.form.submit();
        }
        function goPrePage(src)
        {
            src.form['queryForm.page'].value = parseInt(src.form['queryForm.page'].value)-1;
            src.form.submit();
        }
        function goPage( pageNum,src )
        {
            src.form['queryForm.page'].value = pageNum;
            src.form.submit();
        }
        function setPageSize( pageSize,src )
        {
            src.form['queryForm.pageSize'].value = pageSize;
            src.form.submit();
        }

        function goSort( sort)
        {
            var lastSort = document.forms[0]['queryForm.sort.property'].value;
            var asc = document.forms[0]['queryForm.sort.ascending'].value;
            if(lastSort==sort){
                if(asc=='true'){
                    asc = 'false';
                }else{
                    asc = 'true';
                }
            }else{
                asc = 'true';
            }
            document.forms[0]['queryForm.sort.ascending'].value = asc;
            document.forms[0]['queryForm.sort.property'].value = sort;
            document.forms[0].submit();
        }

        function makePageAction(form){
            if(form.method.value.substr(0,1)!='_'){
                form.method.value='_' + form.method.value;
            }
        }

        function getForm(){
            return document.forms[0];
        }

        function doSort(f){
            var form = getForm();
            makePageAction(form);
            form.sort.value=f;
            form.submit();
        }

