import React from 'react';
import { useEffect } from 'react';
import { useDispatch, useSelector } from 'react-redux';
import { Box, Select, Text, Grid, DateInput, Button } from 'grommet';
import { Search } from 'grommet-icons';
import { fetchFactoriesAsync, selectFactoryList,
    selectSelectedFactory, setSelectedFactory,
    selectStartDate, setStartDate,
    selectEndDate, setEndDate,
    fetchDailyProductionAsync,
    setDisplayMessage
} from './factorySlice';
import { parseISO, isFuture, isAfter } from 'date-fns';


export function SearchBar() {

    const dispatch = useDispatch();
    const factoryList = useSelector(selectFactoryList);
    const selectedFactory = useSelector(selectSelectedFactory);
    const startDate = useSelector(selectStartDate);
    const endDate = useSelector(selectEndDate);

    // fetch energy factory list once
    useEffect(() => {
        dispatch(fetchFactoriesAsync());
    }, []);

    const submitSearch = (factory, startDate, endDate) => {

        if (factory.id === undefined) {
            dispatch(setDisplayMessage("factory must not be blank"));
            return;
        }

        const request = {
            factoryId: factory.id,
            startDate: parseISO(startDate),
            endDate: parseISO(endDate)
        };

        const validationMessage = validateRequest(request);
        if (validationMessage != null) 
            dispatch(setDisplayMessage(validationMessage));
        else 
            dispatch(fetchDailyProductionAsync(request));
    }

    const validateRequest = (request) => {
        if (!request.factoryId || request.factoryId.trim().length === 0)
            return "factory must not be blank";
        
        if (isFuture(request.startDate))
            return "Start Date must not be a date in future";

        if (isFuture(request.endDate))
            return "End Date must not be a date in future";

        if (isAfter(request.startDate, request.endDate))
            return "Start Date must not be after End Date";

        return null;
    }

    return (
        <Grid
            columns={{count: 3, size: 'auto'}}
            gap="small" align='center'>
                <Box>
                    <Box justify="center">
                        <Text size="medium" weight="bold">
                        Wind factory
                        </Text>
                    </Box>
                    <Select                    
                        options={factoryList}
                        labelKey="name"
                        valueKey="id"
                        placeholder="Select a factory"
                        value={selectedFactory}
                        size="medium"
                        onChange={({ value: nextValue }) => {
                            dispatch(setSelectedFactory(nextValue));
                        }}
                        // closeOnChange={true}
                    />
                </Box>
                <Box>
                    <Box justify="center">
                        <Text size="medium" weight="bold">Start Date</Text>
                    </Box>
                    <Box width="medium">
                        <DateInput format="dd/mm/yyyy" value={startDate} 
                                    onChange={({ value: nextValue }) => {
                                        dispatch(setStartDate(nextValue));
                                    }} />
                    </Box>
                </Box>
                <Box>
                    <Box justify="center">
                        <Text size="medium" weight="bold">End Date</Text>
                    </Box>
                    <Box width="medium">
                        <DateInput format="dd/mm/yyyy" value={endDate} 
                                    onChange={({ value: nextValue }) => {
                                        dispatch(setEndDate(nextValue));
                                    }} />
                    </Box>
                </Box>
            <Box>
                <Button label="Search" icon={<Search/>} align="center"
                 onClick={() => submitSearch(selectedFactory, startDate, endDate)}/>
            </Box>
        </Grid>
    );
}