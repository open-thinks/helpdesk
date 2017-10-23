function getTree() {
var tree = [
  {
    text: "Quick Tools",
    nodes: [
      {
        text: "Dev",
        nodes: [
          {
            text: "Eclipse"
          },
          {
            text: "Atom"
          }
        ]
      },
      {
        text: "Email"
      }
    ]
  },
  {
    text: "Notes",
    nodes: [
      {
        text: "MarkDown",
        nodes: [
          {
            text: "Set up Dev env"
          },
          {
            text: "Getting started"
          }
        ]
      },
      {
        text: "PDF"
      },
      {
        text: "WORD"
      }
    ]
  },
  {
    text: "Quick Links",
    nodes: [
      {
        text: "Broswer",
        nodes: [
          {
            text: "Java"
          },
          {
            text: "Github"
          }
        ]
      },
      {
        text: "Local ShortLink"
      }
    ]
  }
  
];
return tree;
}

$('#tree').treeview({data: getTree()});

function setFullHeight($panel){
	var total = document.documentElement.clientHeight;
	var deskHeight = total-$panel[0].offsetTop;
	$panel[0].height=deskHeight+"px";
	$panel.css({
		"max-height":deskHeight+"px",
		"height":deskHeight+"px"
	});
}

$(function(){
	setFullHeight($("#desk"));
	setFullHeight($("#left-panel"));
	setFullHeight($("#main-panel"));
	
	$(window).resize(function(){
		setFullHeight($("#desk"));
		setFullHeight($("#left-panel"));
		setFullHeight($("#main-panel"));
	});
});
