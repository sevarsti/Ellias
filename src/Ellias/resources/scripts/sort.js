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

          function goFirstPage(src){
              makePageAction(src.form);
              src.form.page.value='first';
              src.form.submit();
          }

          function goPrvPage(src){
              makePageAction(src.form);
              src.form.page.value='previous';
              src.form.submit();
          }

          function goNextPage(src){
              makePageAction(src.form);
              src.form.page.value='next';
              src.form.submit();
          }

          function goLastPage(src){
              makePageAction(src.form);
              src.form.page.value='last';
              src.form.submit();
          }

          function goPage(src,page){
              makePageAction(src.form);
              src.form.page.value=page;
              src.form.submit();
          }

          function setPageSize(src,pageSize){
              makePageAction(src.form);
              src.form.pageSize.value=pageSize;
              src.form.submit();
          }