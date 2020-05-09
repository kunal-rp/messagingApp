import { createMuiTheme } from '@material-ui/core/styles';

export const theme = function(themeType){
	return createMuiTheme({
	  palette: {
	    primary:{
	      main: '#ff433f',
	     },
	    contrastThreshold: 3,
	    tonalOffset: 0.2,
	    type: themeType
	  },
})};

