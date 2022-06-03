import React from 'react';
import { useSelector } from 'react-redux';
import { Box, Text } from 'grommet';
import { selectStatus, selectDailyProductionData, selectDisplayMessage } from './factorySlice';
import { Status } from './constants';
import { SearchBar } from './SearchBar';
import { ResultTable } from './ResultTable';
import { LoadingLayer } from './LoadingLayer';
import { MessageDialog } from './MessageDialog';


export function DailyProduction() {

  const isLoading = (useSelector(selectStatus) === Status.loading);
  const displayMessage = useSelector(selectDisplayMessage);
  const showMessageDialog = (displayMessage != null && displayMessage.trim().length > 0);
  
  const dailyProductionData = useSelector(selectDailyProductionData);
  
  return (
    <Box direction="column" gap="medium" margin={{ top: "medium" }}>
        <Box>
          <SearchBar/>
        </Box>
        <Box pad="none" margin="none">
          <Text><sup><font color='red'>*</font></sup> Some hour data is missing</Text>
        </Box>
        <Box pad="small" margin="none" border>
          <ResultTable data={dailyProductionData} />
        </Box>
        {isLoading && (<LoadingLayer/>)}
        {showMessageDialog && (<MessageDialog message={displayMessage} />)}
    </Box>  
  );
}
