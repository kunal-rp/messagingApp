import React from 'react';
import Grid from '@material-ui/core/Grid';
import Button from '@material-ui/core/Button';
import {DASH_SUBTITLE} from '../../utils/Messages'
import GenPage from '../GenPage'

class DashComp extends React.Component{

	constructor(props){
		super(props)
	}

	render() {
		return (
			<div>
				<GenPage>
					<Button variant="contained"> Sample Call </Button>
					<Grid container spacing={1}>
						<Grid item xs={12}>
							{DASH_SUBTITLE}
						</Grid>
					</Grid>	
				</GenPage>		
			</div>);
	}

}

export default DashComp;
