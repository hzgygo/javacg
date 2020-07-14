		mtype:"POST",
        postData:postData,
        rowNum:pageRows,    
        page:currentPage,
        rowList: [10,20,30,40,50],
        pager: "#pagered",
        height:"350",
        width:$(window).innerWidth()-25,
        rownumbers:true,//显示行号
        viewrecords: true,
        multiselect: false,
        shrinkToFit: false,
        jsonReader: {
        	repeatitems: false
        },
    	loadComplete:loadComplete,
        caption:"${viewConfig.authName}",
        toolbar: [true,"top"]