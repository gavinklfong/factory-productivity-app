import React from 'react';
import { DataTable, Text } from 'grommet';

export function ResultTable(props) {

    const percentFormatOption = {
        style: 'percent',
        minimumFractionDigits: 0,
        maximumFractionDigits: 0
     };
     const percentFormatter = new Intl.NumberFormat("en-US", percentFormatOption);

    const columns = [
        {
          property: 'date',
          header: <Text>Date</Text>,
          render: (datum) =>
            datum.date && new Date(datum.date).toLocaleDateString('en-US'),
          align: 'center',
          primary: true,
        },
        {
            property: 'capacityFactor',
            header: <Text>Capacity Factor</Text>,
            render: (datum) => (
                datum.capacityFactor && percentFormatter.format(datum.capacityFactor)
            ),
            align: 'center'
        },
        {
          property: 'production',
          header: <Text>Production</Text>,
          render: (datum) => {
            const partial = (datum.partial)? "*" : "";
            return <Text>{datum.production}<sup><font color='red'>{partial}</font></sup></Text>
          },
          align: 'center'
        }
      ];

    return (        
        <DataTable columns={columns} data={props.data} step={10} size="medium" />
    );
}