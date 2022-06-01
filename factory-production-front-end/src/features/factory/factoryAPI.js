// import { DAILY_PRODUCTION_DATA, FOOD_FACTORIES } from './sample-data';
import { formatISO } from 'date-fns';
import { ResponseStatus } from './constants';

export const fetchDailyProductionAPI = async (factoryId, startDate, endDate) => {
    const formattedStartDate = formatISO(startDate, { representation: 'date' });
    const formattedEndDate = formatISO(endDate, { representation: 'date' });
    const queryUrl = `/factories/${factoryId}/daily-production?startDate=${formattedStartDate}&endDate=${formattedEndDate}`;
    return consumeGetAPI(queryUrl);
}

export const fetchFoodFactoriesAPI = async () => {
    const queryUrl = `/factories?type=FOOD_FACTORY`;
    return consumeGetAPI(queryUrl, convertFactoryData);
}

const consumeGetAPI = async (queryUrl, convertData) => {

    console.log("GET url: " + queryUrl);

    try {
        const response = await fetch(queryUrl);
        if (response.status >= 200 && response.status <= 299) {
            let data = await response.json();
            if (convertData !== undefined) data = convertData(data);
            return {status: ResponseStatus.ok, data: data};  
        } else if (response.status === 404) {
            return {status: ResponseStatus.notFound};  
        } else if (response.status === 400) {
            return {status: ResponseStatus.badRequest};  
        } else {
            return {status: ResponseStatus.exception};  
        }
    } catch (error) {
        console.error(error);
        return {status: ResponseStatus.exception};
    }
}

const convertFactoryData = (apiResponse) => {
    const factoryList = [];
    if (!apiResponse) return factoryList;
    for (const item of apiResponse) {
        factoryList.push(
            {
                id: item.id,
                name: item.name
            }
        );
    };
    return factoryList;
}

// export const fetchDailyProduction = (farmId, startDate, endDate) => {
//     return new Promise((resolve) =>
//       setTimeout(() => resolve({ data: DAILY_PRODUCTION_DATA }), 500)
//     );
// }
  
// export const fetchFoodFactoriesAPI = () => {
//     return new Promise((resolve) =>
//         setTimeout(() => resolve({ data: convertFactoryData(FOOD_FACTORIES) }), 500)
//     );
// }
