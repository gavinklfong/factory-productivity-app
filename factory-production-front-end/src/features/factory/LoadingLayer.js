import React from 'react';
import { Box, Spinner, Text, Layer } from 'grommet';

export function LoadingLayer() {

    return (
        <Layer position="center">
          <Box
            align="center"
            justify="center"
            gap="small"
            direction="row"
            alignSelf="center"
            pad="large"
          >
            <Spinner />
           <Text>Loading...</Text>
          </Box>
        </Layer>
    );
}